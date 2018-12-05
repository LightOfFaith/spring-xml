package com.share.lifetime.util;

import org.junit.Test;

import com.share.lifetime.support.netty.server.NettyServer;

public class NettyServerTest {

    @Test
    public void test() throws Exception {
        NettyServer server = new NettyServer(8080);
        server.run();
    }

}