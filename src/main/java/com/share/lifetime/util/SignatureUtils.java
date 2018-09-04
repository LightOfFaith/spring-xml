package com.share.lifetime.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SignatureUtils {

	public static final String CHARSET_UTF8 = "UTF-8";

	private static final String ALGORITHM_MD5 = "MD5";

	public static final String SIGN = "sign";

	public static final String SIGN_TYPE = "sign_type";

	public static Map<String, String> getSortedMap(Map<String, String> params) {
		Map<String, String> sortedParams = new TreeMap<String, String>();
		if (params != null && params.size() > 0) {
			sortedParams.putAll(params);
		}
		return sortedParams;
	}

	public static String getSignContent(Map<String, String> sortedParams) {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(sortedParams.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = sortedParams.get(key);
			if (org.apache.commons.lang3.StringUtils.isNoneBlank(key, value)) {
				content.append((index == 0 ? "" : "&") + key + "=" + value);
				index++;
			}
		}
		return content.toString();
	}

	public static String getSignCheckContentV1(Map<String, String> params) {
		if (params == null) {
			return null;
		}

		params.remove(SIGN);
		params.remove(SIGN_TYPE);

		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			content.append((i == 0 ? "" : "&") + key + "=" + value);
		}

		return content.toString();
	}

	public static String getSignCheckContentV2(Map<String, String> params) {
		if (params == null) {
			return null;
		}

		params.remove(SIGN);

		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			content.append((i == 0 ? "" : "&") + key + "=" + value);
		}

		return content.toString();
	}


}
