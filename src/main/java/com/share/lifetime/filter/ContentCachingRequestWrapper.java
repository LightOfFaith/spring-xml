package com.share.lifetime.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.exception.ExceptionUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContentCachingRequestWrapper extends HttpServletRequestWrapper {

	private String characterEncoding;
	private String cachedContent;

	public ContentCachingRequestWrapper(HttpServletRequest request) {
		super(request);
		ServletInputStream inputStream = null;
		InputStreamReader streamReader = null;
		BufferedReader reader = null;
		StringWriter writer = null;
		try {
			String characterEncoding = request.getCharacterEncoding();
			inputStream = request.getInputStream();
			if (org.apache.commons.lang3.StringUtils.isEmpty(characterEncoding)) {
				streamReader = new InputStreamReader(inputStream);
			} else {
				streamReader = new InputStreamReader(inputStream, characterEncoding);
			}
			reader = new BufferedReader(streamReader);
			writer = new StringWriter();
			char cbuf[] = new char[255];
			int len = reader.read(cbuf);
			while (len != -1) {
				writer.write(cbuf, 0, len);
				len = reader.read(cbuf);
			}
			cachedContent = writer.toString();
		} catch (IOException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (reader != null) {
					reader.close();
				}
				if (streamReader != null) {
					streamReader.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				log.error(ExceptionUtils.getStackTrace(e));
			}
		}

	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		byte[] bytes;
		if (org.apache.commons.lang3.StringUtils.isEmpty(characterEncoding)) {
			bytes = cachedContent.getBytes();
		} else {
			bytes = cachedContent.getBytes(characterEncoding);
		}
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
		InputStreamReader streamReader = null;
		if (org.apache.commons.lang3.StringUtils.isEmpty(characterEncoding)) {
			streamReader = new InputStreamReader(getInputStream());
		} else {
			streamReader = new InputStreamReader(getInputStream(), characterEncoding);
		}
		return new BufferedReader(streamReader);
	}


}
