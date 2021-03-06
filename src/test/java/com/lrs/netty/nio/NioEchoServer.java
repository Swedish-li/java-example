package com.lrs.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NioEchoServer {
    private static final int BUF_SIZE = 256;
    private static final int TIMEOUT = 3000;

    public static void main(String[] args) throws IOException {
        // 服务端 Socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 打开Selector
        Selector selector = Selector.open();

        // 获取socket进行端口绑定
        serverSocketChannel.socket().bind(new InetSocketAddress(8088));
        serverSocketChannel.configureBlocking(false);

        // 将channel注册到 selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 循环监听连接请求
        while (true) {
            // 阻塞等待超时时间 channel I/O 可以操作
            if (selector.select(TIMEOUT) == 0) {
                // 到达监听状态的keys数为0，继续等待
                System.out.println(".");
                continue;
            }
            // 获取selectionKey的迭代器
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();

            while (keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();
                // 就绪准备连接
                if (key.isAcceptable()) {
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(BUF_SIZE));

                }
                // 使用SocketChannel来读写数据，使用key来获取数据缓冲（ByteBuffer）
                // 数据读取状态
                if (key.isReadable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    long bytesRead = clientChannel.read(buffer);
                    if (bytesRead == -1) {
                        // 未读取到数据
                        clientChannel.close();
                    } else if (bytesRead > 0) {
                        key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        System.out.println("Get data length:" + bytesRead);
                        String str = byteBufferToString(buffer);
                        System.out.println(str);
                    }
                }
                // 写数据状态
                if (key.isValid() && key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    buffer.flip();
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    clientChannel.write(buffer);
                    if (!buffer.hasRemaining()) {
                        // 数据写入完成
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    buffer.compact();
                }

                keyIterator.remove();
            }

        }
    }

    /**
     * 将ByteBuffer转换为字符串
     *
     * @param buffer
     * @return
     */
    private static String byteBufferToString(ByteBuffer buffer) {
        CharBuffer charBuffer = null;
        try {
            Charset charset = StandardCharsets.UTF_8;
            CharsetDecoder decoder = charset.newDecoder();
            buffer.flip();
            charBuffer = decoder.decode(buffer);
            return charBuffer.toString();
        } catch (Exception ex) {
            throw new RuntimeException("ByteBuffer数据读取失败！", ex);
        }
    }
}
