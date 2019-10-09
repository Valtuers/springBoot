package com.lmc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("客户端收到："+msg.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用LineBasedFrameDecoder
//        ByteBuf buf = null;
//        byte[] bytes = ("哈哈哈").getBytes();
//        for (int i=0;i<10;i++){
//            buf = Unpooled.buffer(bytes.length);
//            buf.writeBytes(bytes);
//            ctx.writeAndFlush(buf);
//        }

        //使用DelimiterBasedFrameDecoder
        String content = "哈哈哈&_啦啦啦&_噢噢噢&_";
        ByteBuf buf = Unpooled.buffer(content.getBytes().length).writeBytes(content.getBytes());
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
