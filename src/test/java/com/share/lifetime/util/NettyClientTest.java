package com.share.lifetime.util;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.share.lifetime.support.netty.RequestData;
import com.share.lifetime.support.netty.ResponseData;
import com.share.lifetime.support.netty.client.NettyClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyClientTest {

    @Test
    public void test() throws InterruptedException, UnsupportedEncodingException {
        RequestData requestData = new RequestData();
        requestData.setValue(
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><head><chnlid>001</chnlid><chnldt>20160802</chnldt><chnltm>09101133</chnltm><chnlsq>000000000000000</chnlsq><prcscd>xxxxx</prcscd></head><body><ordrno>1111111</ordrno><bodyds>商品描述--只会用功不玩耍，聪明孩子也变傻</bodyds><clieip>127.0.0.1</clieip></body></root>");
        String host = "localhost";
        int port = 8080;

        NettyClient client = new NettyClient(host, port, requestData);
        ResponseData sendRequest = client.sendRequest();
        log.info(sendRequest.getValue());
    }

}
