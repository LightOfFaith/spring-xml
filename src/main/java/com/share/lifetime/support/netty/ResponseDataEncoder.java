package com.share.lifetime.support.netty;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 响应编码器
 * @author liaoxiang
 * @date 2018/11/11
 */
public class ResponseDataEncoder extends MessageToByteEncoder<ResponseData> {

    private final Charset charset = Charset.forName("UTF-8");
    
    @Override
    protected void encode(ChannelHandlerContext ctx, ResponseData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getIntValue());
        out.writeCharSequence(msg.getStringValue(), charset);
    }

}
