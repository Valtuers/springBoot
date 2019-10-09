package com.lmc.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 抽象解码器
 * ByteToMessageDecoder: 用于将字符转为消息，需要检查缓冲区是否有足够的字节
 * ReplayingDecoder: 继承ByteToMessageDecoder。不需要检查缓冲区
 * MessageToMessageDecoder: 用于从一种消息解码到另外一种消息，例如：POJO => POJO
 */

/**
 * 解码器具体实现
 * DelimiterBasedFrameDecoder: 指定消息分割符的解码器  xxxx&&xxxx
 * LineBasedFrameDecoder: 以换行符为结束标志的解码器
 * FixedLengthFrameDecoder: 固定长度的解码器
 * LengthFieldBaseFrameDecoder: message = header+body,基于长度解码的通用解码器
 * StringDecoder: 文本解码，将接收到的消息转为字符串，一般与上面的集中组合，然后在后面加业务的handler
 */
public class CustomDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //添加到解码信息里面去
        out.add(in.readInt());
        //发送到下一个handler
        ctx.fireChannelRead(out);
    }
}
