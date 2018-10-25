package com.share.lifetime.util;

import java.util.Collection;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.alibaba.fastjson.JSONObject;


public class JsonUtils {

	public static String toJson(final Object object) {
		return ReflectionToStringBuilder.toString(object, ToStringStyle.JSON_STYLE);
	}

	public static String toJsonExclude(final Object object, final Collection<String> excludeFieldNames) {
		String stringExclude = ReflectionToStringBuilder.toStringExclude(object, excludeFieldNames);
		return JSONObject.toJSONString(stringExclude);
	}


}
