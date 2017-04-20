package com.lrs.netty;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.Test;

import lombok.val;

/**
 * NIO特性使用示例
 * 
 * 1、基于Buffer(Buffer Oriented) 2、操作是非阻塞的 3、有selector的概念
 * 
 * @author Swedish-li
 *
 */
public class NioTest {

	/**
	 * FileChannel
	 * 
	 * @throws IOException
	 */
	@Test
	public void channelBaseExample() throws IOException {
		String path = NioTest.class.getResource("/").getPath() + File.separator + "template" + File.separator
				+ "inc.ftl";
		RandomAccessFile randomAccessFile = new RandomAccessFile(new File(path), "rw");
		FileChannel inChannel = randomAccessFile.getChannel();
		// 分配空间
		ByteBuffer buf = ByteBuffer.allocate(48);
		// 将channel中的数据读取到 Buffer中
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
			buf.flip();
			while (buf.hasRemaining()) {
				System.out.print((char) buf.get());
			}
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
		serverSocketChannel.socket().bind(new InetSocketAddress( 9999));
		
		while (true) {
			SocketChannel socketChannel = serverSocketChannel.accept();	
			ByteBuffer buffer = ByteBuffer.allocate(40);
			socketChannel.read(buffer);
			printBuffer(buffer);
		}
	}
		
	
	public void printBuffer(ByteBuffer buffer) {
		// 将Buffer转换为读模式
		buffer.flip();
		
		while (buffer.hasRemaining()) {
			System.out.print(buffer.get());
		}
	}
}
