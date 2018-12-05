package com.share.lifetime.support.netty;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.EmptyByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.val;

/**
 * 请求编码器
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
public class RequestDataEncoder extends MessageToByteEncoder<RequestData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf out) throws Exception {
        ByteBuf in = ctx.alloc().buffer(4);
        String value = msg.getValue();
        byte[] src = value.getBytes();
        in.writeBytes(src);
        int length = src.length;
        out.writeInt(length);
        out.writeCharSequence(msg.getValue(), charset);
    }

}
