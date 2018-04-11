package com.lrs.netty.simple.time;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 不接收客户端请求的任何信息，仅在连接建立以后向客户端发送32位的整数信息，并且关闭连接
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UnixTime time = new UnixTime();
        ChannelFuture future = ctx.writeAndFlush(time);
        future.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
