package com.share.lifetime.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServletRequestCachingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("==========HttpServletRequestCachingFilter doFilter==========");
		com.share.lifetime.filter.ContentCachingRequestWrapper requestCacheWrapperObject = null;
		if (request instanceof HttpServletRequest) {
			HttpServletRequest servletRequest = (HttpServletRequest) request;
			String method = servletRequest.getMethod();// GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
			String contentType = servletRequest.getContentType();
			if (("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method))
					&& (contentType.contains(MediaType.APPLICATION_JSON_VALUE)
							|| contentType.contains(MediaType.TEXT_PLAIN_VALUE))) {
				requestCacheWrapperObject = new com.share.lifetime.filter.ContentCachingRequestWrapper(servletRequest);
			}
		}
		if (requestCacheWrapperObject == null) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(requestCacheWrapperObject, response);
		}
	}

	@Override
	public void destroy() {

	}

}
