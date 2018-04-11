package com.lrs.netty.nio;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * NIO特性使用示例
 * <p>
 * Java NIO 教程： http://ifeve.com/java-nio-all/
 * <p>
 * 1、基于Buffer(Buffer Oriented) 2、操作是非阻塞的 3、有selector的概念
 *
 * @author Swedish-li
 */
public class NioTest {

    Logger logger = LoggerFactory.getLogger(NioTest.class);

    // http://ifeve.com/buffers/
    @Test
    public void testBuffer() {
        // 分配10个单位空间
        IntBuffer buffer = IntBuffer.allocate(10);
        buffer.put(10);
        buffer.put(56);
        logger.info("Buffer: mode:Write,capacity:{},limit:{},position:{}",
                new Object[]{buffer.capacity(), buffer.limit(), buffer.position()});
        // 切换为读模式
        buffer.flip();
        logger.info("Buffer: mode:Read,capacity:{},limit:{},position:{}",
                new Object[]{buffer.capacity(), buffer.limit(), buffer.position()});
    }

    /**
     * Buffer
     * <p>
     * 1、capacity（容量）: 最多保存的单位数据的数量
     * <p>
     * 2、limit(和读写模式有关)：最多可以读取或写入的数据量
     * <p>
     * 3、position(和读写模式有关)=>读写操作的位置指针
     */
    @Test
    public void testIntBuffer() {
        // 分配2个单位的空间
        IntBuffer buffer = IntBuffer.allocate(2);
        // 向Buffer中写入数据
        buffer.put(12345678);
        buffer.put(8);
        // 切换为读模式
        buffer.flip();
        // 读取数据
        // 每当调用一次 get 方法读取数据时, buffer 的读指针都会向前移动一个单位长度(在这里是一个 int 长度)
        println(buffer.get());
        println(buffer.get());
    }

    private void println(Object ob) {
        System.out.println(ob);
    }

    /**
     * FileChannel
     * <p>
     * java中的Nio和流的区别
     * <p>
     * 1、既可以从通道中读取数据，又可以向通道中写数据
     * <p>
     * 2、通道可以异步读写
     * <p>
     * 3、通道总是通过Buffer来读写数据
     *
     * @throws IOException
     */
    @Test
    public void channelBaseExample() throws IOException {
        String path = NioTest.class.getResource("/").getPath() + File.separator + "template" + File.separator
                + "inc.ftl";
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File(path), "rw");
        FileChannel inChannel = randomAccessFile.getChannel();
        // 分配48个单位的空间
        ByteBuffer buf = ByteBuffer.allocate(48);
        // 将channel中的数据读取到 Buffer中
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            // 切换buf为读状态
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            // 转换buf为写模式，也可以使用compact()方法
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        // 关闭资源
        inChannel.close();
        randomAccessFile.close();
    }

    @Test
    public void socketChannelExample() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        // 设置socketChannel为异步模式
        // socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        String data = "Hello Nio Socket Channel!";
        ByteBuffer buffer = ByteBuffer.allocate(40);
        buffer.clear();
        buffer.put(data.getBytes());
        buffer.flip();
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);

        }
    }

    @Test
    public void serverSocketChannelExample() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(40);
            socketChannel.read(buffer);
            printBuffer(buffer);
        }
    }

    /**
     * SelectionKey代表一个通道（channel）在 Selector上的注册
     */

    @Test
    public void testSelectionKey() {
        // SelectionKey.OP_ACCEPT : 1 << 0
        logger.info("OP_ACCEPT(1 << 0):" + SelectionKey.OP_ACCEPT);
        logger.info("OP_CONNECT(1 << 2):" + SelectionKey.OP_CONNECT);
        logger.info("OP_READ(1 << 3):" + SelectionKey.OP_READ);
        logger.info("OP_WRITE(1 << 4):" + SelectionKey.OP_WRITE);
    }

    public void printBuffer(ByteBuffer buffer) {
        // 将Buffer转换为读模式
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
        System.out.println();
        // 将buffer转换为写模式
        buffer.clear();
    }
}
