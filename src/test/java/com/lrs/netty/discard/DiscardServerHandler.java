package com.lrs.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 
 * 服务端 channel 处理 1、数据读取 2、异常处理
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	/**
	 * 当接收到消息的时候次方法会被调用
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// 抛弃接收到的数据，无论何时从客户端接收到消息，这个消息的类型都为 ByteBuf
		// ByteBuf 对象为引用计数类型的对象，必须显示通过 release()方法释放
		ByteBuf in = (ByteBuf) msg;
		try {
			while (in.isReadable()) {
				System.out.println(in.toString(CharsetUtil.US_ASCII));
			}

		} finally {
			ReferenceCountUtil.release(msg);
		}
	}

	/**
	 * 处理netty产生的I/O错误或处理器实现产生的异常 异常需要被日志记录，相关的channel需要被在此关闭
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		// 当发生异常时关闭连接
		ctx.close();
	}

}
