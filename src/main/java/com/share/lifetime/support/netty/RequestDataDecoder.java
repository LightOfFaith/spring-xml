package com.share.lifetime.support.netty;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * 请求解码器
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
public class RequestDataDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        RequestData data = new RequestData();
        int strLen = in.readInt();
        data.setValue(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }

}
