package com.share.lifetime.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import com.dangdang.ddframe.job.util.env.HostException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 获取真实本机网络的服务.
 * 
 * @author liaoxiang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPUtils {

	/**
	 * IP地址的正则表达式.
	 */
	public static final String IP_REGEX = "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})";

	private static volatile String cachedIpAddress;

	private static String localIp;

	/**
	 * 获取本机IP地址.
	 * 
	 * <p>
	 * 有限获取外网IP地址. 也有可能是链接着路由器的最终IP地址.
	 * </p>
	 * 
	 * @return 本机IP地址
	 */
	public static String getIp() {
		if (null != cachedIpAddress) {
			return cachedIpAddress;
		}
		Enumeration<NetworkInterface> netInterfaces;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (final SocketException ex) {
			throw new HostException(ex);
		}
		String localIpAddress = null;
		while (netInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = netInterfaces.nextElement();
			Enumeration<InetAddress> ipAddresses = netInterface.getInetAddresses();
			while (ipAddresses.hasMoreElements()) {
				InetAddress ipAddress = ipAddresses.nextElement();
				if (isPublicIpAddress(ipAddress)) {
					String publicIpAddress = ipAddress.getHostAddress();
					cachedIpAddress = publicIpAddress;
					return publicIpAddress;
				}
				if (isLocalIpAddress(ipAddress)) {
					localIpAddress = ipAddress.getHostAddress();
				}
			}
		}
		cachedIpAddress = localIpAddress;
		return localIpAddress;
	}

	private static boolean isPublicIpAddress(final InetAddress ipAddress) {
		return !ipAddress.isSiteLocalAddress() && !ipAddress.isLoopbackAddress() && !isV6IpAddress(ipAddress);
	}

	private static boolean isLocalIpAddress(final InetAddress ipAddress) {
		return ipAddress.isSiteLocalAddress() && !ipAddress.isLoopbackAddress() && !isV6IpAddress(ipAddress);
	}

	private static boolean isV6IpAddress(final InetAddress ipAddress) {
		return ipAddress.getHostAddress().contains(":");
	}

	/**
	 * 获取本机Host名称.
	 * 
	 * @return 本机Host名称
	 */
	public static String getHostName() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (final UnknownHostException ex) {
			return "unknown";
		}
	}

	/**
	 * 获取本机的网络IP
	 */
	public static String getLocalNetWorkIp() {
		if (localIp != null) {
			return localIp;
		}
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			while (netInterfaces.hasMoreElements()) {// 遍历所有的网卡
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				if (ni.isLoopback() || ni.isVirtual()) {// 如果是回环和虚拟网络地址的话继续
					continue;
				}
				Enumeration<InetAddress> addresss = ni.getInetAddresses();
				while (addresss.hasMoreElements()) {
					InetAddress address = addresss.nextElement();
					if (address instanceof Inet4Address) {// 这里暂时只获取ipv4地址
						ip = address;
						break;
					}
				}
				if (ip != null) {
					break;
				}
			}
			if (ip != null) {
				localIp = ip.getHostAddress();
			} else {
				localIp = "127.0.0.1";
			}
		} catch (Exception e) {
			localIp = "127.0.0.1";
		}
		return localIp;
	}

}
