package com.share.lifetime.support.netty.client;

import com.share.lifetime.support.netty.RequestData;
import com.share.lifetime.support.netty.ResponseData;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        RequestData msg = new RequestData();
        msg.setIntValue(123);//
        msg.setStringValue(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><head><chnlid>001</chnlid><chnldt>20160802</chnldt><chnltm>09101133</chnltm><chnlsq>000000000000000</chnlsq><prcscd>xxxxx</prcscd></head><body><ordrno>1111111</ordrno><bodyds>商品描述--只会用功不玩耍，聪明孩子也变傻</bodyds><clieip>127.0.0.1</clieip></body></root>只会用功不玩耍，聪明孩子也变傻");//
        // <?xml version=\"1.0\"
        // encoding=\"UTF-8\"?><root><head><chnlid>001</chnlid><chnldt>20160802</chnldt><chnltm>09101133</chnltm><chnlsq>000000000000000</chnlsq><prcscd>xxxxx</prcscd></head><body><ordrno>1111111</ordrno><bodyds>商品描述--只会用功不玩耍，聪明孩子也变傻</bodyds><clieip>127.0.0.1</clieip></body></root>
        log.info("msg:{}", msg);

        ChannelFuture future = ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("{}", (ResponseData)msg);
        ctx.close();
    }
}
