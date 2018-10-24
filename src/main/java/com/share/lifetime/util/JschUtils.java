package com.share.lifetime.util;

import java.io.IOException;
import java.io.InputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author hasee
 * @see http://www.jcraft.com/jsch/
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JschUtils {

	private static Session getSession(String host, int port, String username, String password) throws JSchException {
		JSch jsch = new JSch();
		Session session = jsch.getSession(username, host, port);
		session.setPassword(password);
		return session;
	}

	/**
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param identity 私钥文件名。
	 * @return
	 * @throws JSchException
	 */
	private static Session getSessionByUserAuthPubKey(String host, int port, String username, String identity)
			throws JSchException {
		JSch jsch = new JSch();
		jsch.addIdentity(identity, "passphrase");
		Session session = jsch.getSession(username, host, port);
		return session;
	}

	public static String exec(String host, String username, String password, String command)
			throws JSchException, IOException {
		return exec(host, 22, username, password, command);
	}

	public static void shell(String host, int port, String username, String password)
			throws JSchException, IOException {
		Session session = getSession(host, port, username, password);
		UserInfo userInfo = new UserInfo() {

			@Override
			public void showMessage(String message) {
				log.info("showMessage---->message:{}", message);
			}

			@Override
			public boolean promptYesNo(String message) {
				log.info("promptYesNo---->message:{}", message);
//				return false;
				return true;
			}

			@Override
			public boolean promptPassword(String message) {
				log.info("promptPassword---->message:{}", message);
				return false;
			}

			@Override
			public boolean promptPassphrase(String message) {
				log.info("promptPassphrase---->message:{}", message);
				return false;
			}

			@Override
			public String getPassword() {
				log.info("getPassword---->");
				return null;
			}

			@Override
			public String getPassphrase() {
				log.info("getPassphrase---->");
				return null;
			}
		};

		session.setUserInfo(userInfo);
//		session.setConfig("StrictHostKeyChecking", "no");
//		session.connect();
		session.connect(3 * 10000);
		ChannelShell shell = (ChannelShell) session.openChannel("shell");
		shell.setInputStream(System.in);
		shell.setOutputStream(System.out);
//		shell.setPtyType("vt102");
//		shell.connect();
		shell.connect(3 * 1000);
	}

	public static String exec(String host, int port, String username, String password, String command)
			throws JSchException, IOException {
		Session session = getSession(host, port, username, password);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		ChannelExec exec = (ChannelExec) session.openChannel("exec");
		exec.setCommand(command);
//		exec.setInputStream(System.in);
		exec.setInputStream(null);
//		exec.setOutputStream(System.out);

		InputStream inputStream = exec.getInputStream();
		exec.connect();
		StringBuilder builder = new StringBuilder();
		byte[] tmp = new byte[1024];
		while (true) {
			while (inputStream.available() > 0) {
				int i = inputStream.read(tmp, 0, 1024);
				if (i < 0)
					break;
				builder.append(new String(tmp, 0, i));
			}
			if (exec.isClosed()) {
				if (inputStream.available() > 0)
					continue;
				builder.append("exit-status: " + exec.getExitStatus());
				break;
			}
		}
		exec.disconnect();
		session.disconnect();
		return builder.toString();
	}

}
