package com.share.lifetime.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HttpClientUtilsTest {

	@Test
	public void testDoGetString() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoGetStringMapOfStringStringString() throws Exception {
		Map<String, String> map = new HashMap<>();
		HttpClientUtils.doGet("http://localhost/spring-xml/rest/errorBiz", map, "UTF-8");
	}

}
