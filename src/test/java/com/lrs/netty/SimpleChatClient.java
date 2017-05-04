package com.lrs.netty;

import java.util.Scanner;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class SimpleChatClient {
	private String host = "localhost";
	private int port = 9089;

	public SimpleChatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public SimpleChatClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		new SimpleChatClient().run();
	}

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap bootstrap = new Bootstrap()
					.group(group).channel(NioSocketChannel.class).handler(new SimpleChatClientInitializer());
			Channel channel = bootstrap.connect(host, port).sync().channel();
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.println("please enter...");
			boolean exit = false;
			while (!exit) {
				String str = sc.next();
				channel.writeAndFlush(str + "\r\t");
				if ("exit".equalsIgnoreCase(str)) {
					exit = true;
					channel.close();
				}
			}
		} finally {
			group.shutdownGracefully();
		}

	}
}
