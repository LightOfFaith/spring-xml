package com.share.lifetime.util;

import java.util.Map;

import com.google.common.collect.Maps;

public class LogContext {

	private static ThreadLocal<Map<String, String>> logContextHolder = new ThreadLocal<Map<String, String>>() {
		@Override
		protected Map<String, String> initialValue() {
			return Maps.newLinkedHashMap();
		}
	};

	public static void reset() {
		logContextHolder.remove();
	}

	public static void put(String key, String value) {
		logContextHolder.get().put(key, value);
	}

	public static String get(String key) {
		return logContextHolder.get().get(key);
	}

	public static Map<String, String> currentLogContext() {
		return logContextHolder.get();
	}

}
