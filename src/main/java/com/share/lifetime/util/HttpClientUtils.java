package com.share.lifetime.util;

import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientUtils {

	public void doGet(String uri) {
		HttpGet httpget = new HttpGet(uri);
		httpget.abort();
	}

	@Autowired
	public static void doGet(String url, Map<String, String> params, String charset) throws Exception {
		if (StringUtils.isEmpty(url) && MapUtils.isEmpty(params)) {

		} else {
			// http://localhost:8080/pay/admin
			String[] split = url.split(":");
			String scheme = split[0];
			String domain = split[1].split(":")[0];
			String host = domain.substring(2);
			int port = Integer.valueOf(split[2].split("/")[0]);
			URIBuilder uriBuilder = new URIBuilder().setScheme(scheme).setHost(host).setPort(port)
					.setPath("/spring-xml/rest/errorBiz");
			BasicNameValuePair nameValuePair = null;
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			Set<Entry<String, String>> entrySet = params.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String name = entry.getKey();
				String value = entry.getValue();
				nameValuePair = new BasicNameValuePair(name, value);
				nvps.add(nameValuePair);
			}
			uriBuilder.setParameters(nvps);

			URI uri = uriBuilder.setCharset(Charset.forName(charset)).build();
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(uri);
			CloseableHttpResponse response = httpclient.execute(httpget);
			StatusLine statusLine = response.getStatusLine();
			log.info("----------------------------------------");
			log.info("{}", statusLine);
			HttpEntity entity = response.getEntity();
			boolean streaming = entity.isStreaming();
			if (streaming) {
				InputStream content = entity.getContent();
				String string = EntityUtils.toString(entity);
				log.info("{}", string);
			}
			log.info("{}", streaming);
		}

	}

}
