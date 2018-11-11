package com.share.lifetime.util;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SftpUtilsTest {

    String host = null;
    int port = 22;
    String username = null;
    String password = null;

    @Before
    public void before() {
        host = "192.168.127.136";
        username = "sftp";
        password = "12345678";
    }

    @Test
    public void testDownload() {
        String src = "/home/sftp/test.txt";
        String dst = "d:\\test.txt";
        boolean download = SftpUtils.download(host, port, username, password, src, dst);
        assertTrue(download);
    }

    @Test
    public void testUpload() {
        String src = "d:\\test.txt";
        String dst = "/home/sftp/test.txt";
        boolean upload = SftpUtils.upload(host, port, username, password, src, dst);
        assertTrue(upload);
    }
}
