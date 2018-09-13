package com.share.lifetime.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.exception.ExceptionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServletRequestBodyWrapper extends HttpServletRequestWrapper {

	private String body;
	private String charset;

	public HttpServletRequestBodyWrapper(HttpServletRequest request) {
		super(request);
		InputStreamReader in = null;
		InputStream stream = null;
		BufferedReader reader = null;
		try {
			stream = request.getInputStream();
			charset = request.getCharacterEncoding();
			if (org.apache.commons.lang3.StringUtils.isEmpty(charset)) {
				in = new InputStreamReader(stream);
			} else {
				in = new InputStreamReader(stream, charset);
			}
			reader = new BufferedReader(in);
			StringWriter writer = new StringWriter();

			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}
			body = writer.toString();
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (in != null) {
					in.close();
				}
				if (stream != null) {
					stream.close();
				}
			} catch (IOException e) {
				log.error(ExceptionUtils.getStackTrace(e));
			}
		}
	}

	@Override
	public BufferedReader getReader() throws IOException {
		Reader in = null;
		if (org.apache.commons.lang3.StringUtils.isEmpty(charset)) {
			in = new InputStreamReader(getInputStream());
		} else {
			in = new InputStreamReader(getInputStream(), charset);
		}
		return new BufferedReader(in);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		byte[] bytes;
		if (org.apache.commons.lang3.StringUtils.isEmpty(charset)) {
			bytes = body.getBytes();
		} else {
			bytes = body.getBytes(charset);
		}
		final ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		return new ServletInputStream() {

			@Override
			public int read() throws IOException {
				return in.read();
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

}
