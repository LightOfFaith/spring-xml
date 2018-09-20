package com.share.lifetime.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.share.lifetime.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentCachingRequestWrapper extends HttpServletRequestWrapper {

	private String cachedContent;

	public ContentCachingRequestWrapper(HttpServletRequest request) {
		super(request);
		try {
			ServletInputStream inputStream = request.getInputStream();
			cachedContent = WebUtils.getStreamAsString(inputStream);
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}

	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		byte[] bytes = cachedContent.getBytes();
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return inputStream.read();
			}

			@Override
			public void setReadListener(ReadListener readListener) {
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public boolean isFinished() {
				return false;
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		InputStreamReader streamReader = new InputStreamReader(getInputStream());
		return new BufferedReader(streamReader);
	}


}
