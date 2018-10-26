package com.share.lifetime.util;

import java.io.IOException;

import org.junit.Test;

import com.jcraft.jsch.JSchException;

public class JschUtilsTest {

	public static void main(String[] args) throws JSchException, IOException {
		JschUtils.shell("192.168.43.145", 22, "root", "aliyun18");
	}

	@Test
	public void testExec() throws JSchException, IOException {
		System.out.println("****" + JschUtils.exec("192.168.43.145", 22, "root", "aliyun18", "ls -a") + "****");
		System.out.println("****" + JschUtils.exec("192.168.43.145", 22, "root", "aliyun18", "ll") + "****");
		System.out.println("****" + JschUtils.exec("192.168.43.145", 22, "root", "aliyun18", "cd /;ls -al") + "****");
	}

}
