package com.share.lifetime.util;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
@Slf4j
public class SftpUtils {

    public static boolean download(String host, int port, String username, String password, String src, String dst) {
        boolean flag = true;
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try {
            session = JschUtils.getSession(host, port, username, password);
            channel = session.openChannel(JschUtils.SFTP_TYPE);
            channel.connect();
            channelSftp = (ChannelSftp)channel;
            channelSftp.get(src, dst);
        } catch (JSchException e) {
            flag = false;
            log.error(ExceptionUtils.getStackTrace(e));
        } catch (SftpException e) {
            flag = false;
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            disconnect(session, channel, channelSftp);
            if (!flag) {
                log.warn("host:{},port:{},username:{},src:{},dst:{}", host, port, username, src, dst);
            }
        }
        return flag;
    }

    public static boolean upload(String host, int port, String username, String password, String src, String dst) {
        boolean flag = true;
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        try {
            session = JschUtils.getSession(host, port, username, password);
            channel = session.openChannel(JschUtils.SFTP_TYPE);
            channel.connect();
            channelSftp = (ChannelSftp)channel;
            channelSftp.put(src, dst);
        } catch (JSchException e) {
            flag = false;
            log.error(ExceptionUtils.getStackTrace(e));
        } catch (SftpException e) {
            flag = false;
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            disconnect(session, channel, channelSftp);
            if (!flag) {
                log.warn("host:{},port:{},username:{},src:{},dst:{}", host, port, username, src, dst);
            }
        }
        return flag;
    }

    private static void disconnect(Session session, Channel channel, ChannelSftp channelSftp) {
        if (channelSftp != null) {
            channelSftp.disconnect();
        }
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
}
