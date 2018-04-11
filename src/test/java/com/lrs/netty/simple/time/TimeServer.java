package com.lrs.netty.simple.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Time protocol(RFC868)
 *
 * @author Swedish-li
 */
public class TimeServer {
    private int port;

    public TimeServer(int port) {
        this.port = port;
    }

    /**
     * 在Linux上使用 rdate命令可以测试该服务,默认使用37端口
     * <p>
     * rdate -p <host>
     */
    public static void main(String[] args) throws InterruptedException {
        new TimeServer(37).run();
    }

    public void run() throws InterruptedException {
        EventLoopGroup main = new NioEventLoopGroup();
        EventLoopGroup child = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(main, child)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeEncoder()).addLast(new TimeServerHandler());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();

        } finally {
            main.shutdownGracefully();
            child.shutdownGracefully();
        }
    }
}
