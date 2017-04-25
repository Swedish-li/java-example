package com.lrs.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NioEchoClient {

	/* 发送数据缓冲区 */
	private static ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

	public static void sendInfo2Server(SocketChannel client) throws IOException {
		sendBuffer.clear();
		sendBuffer.put((new Date() + "--客户端数据发送").getBytes());
		sendBuffer.flip();
		client.write(sendBuffer);
		System.out.println("客户端数据发送成功!");
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8088));
		while (true) {
			if (socketChannel.isConnected()) {
				System.out.println("服务端已连接！");
				sendInfo2Server(socketChannel);
			} else {
				System.out.println("服务端尚未连接！");
			}

			TimeUnit.SECONDS.sleep(3);

		}
	}
}
