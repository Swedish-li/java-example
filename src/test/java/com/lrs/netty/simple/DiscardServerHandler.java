package com.lrs.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

// 丢弃接收到的信息，并且没有任何回复

/**
 * 服务端一侧通道处理器
 *
 * @author Swedish-li
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        try {
            while (buf.isReadable()) {
                System.out.println(buf.toString(io.netty.util.CharsetUtil.US_ASCII));
            }

        } finally {
            ReferenceCountUtil.release(buf);
        }

        // ByteBuf是引用计数型对象，必须通过方法release()显示清理

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当产生异常的时候关闭连接
        cause.printStackTrace();
        ctx.close();
    }

}
