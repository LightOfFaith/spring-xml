package com.share.lifetime.support.netty;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

/**
 * 响应数据解码
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
public class ResponseDataDecoder extends ReplayingDecoder<ResponseData> {

    private final Charset charset = Charset.forName("UTF-8");

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ResponseData data = new ResponseData();
        int strLen = in.readInt();
        data.setValue(in.readCharSequence(strLen, charset).toString());
        out.add(data);
    }

}
