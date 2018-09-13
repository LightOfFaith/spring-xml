package com.share.lifetime.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.share.lifetime.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private static final String GET = "GET";
	private static final String POST = "POST";
	private static final String PUT = "PUT";

	private static final String LOG_TEMPLATE = "receive request method:[%s],content type:[%s],charset:[%s],url:[%s],IP:[%s]";

	/**
	 * 在执行实际处理程序之前调用，但尚未生成视图 该方法返回一个布尔值 -
	 * 它告诉Spring请求是否应由处理程序进一步处理（true）或不处理（false）。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return true;
	}

	/**
	 * 在执行处理程序后调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String charset = request.getCharacterEncoding();
		String method = request.getMethod();
		String contentType = request.getContentType();
		StringBuffer requestURL = request.getRequestURL();
		String remoteAddr = getRemoteAddr(request);
		log.info(String.format(LOG_TEMPLATE, method, contentType, charset, requestURL, remoteAddr));
		String parameters = getParameters(request);
		log.info("request.getParameterNames:{},{}", parameters, StringUtils.isEmpty(parameters));
		log.info("request.getParameterMap():{}", JSONObject.toJSONString(request.getParameterMap()));
		String streamAsString = WebUtils.getStreamAsString(request.getInputStream(), charset);
		log.info("request.getInputStream():{},{}", streamAsString, StringUtils.isEmpty(streamAsString));
		super.postHandle(request, response, handler, modelAndView);
	}

	/**
	 * 在HandlerAdapter处理请求之后，但在生成视图之前立即调用此方法。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	private String getParameters(HttpServletRequest request) {

		StringBuffer posted = new StringBuffer();
		Enumeration<?> e = request.getParameterNames();
		if (e != null) {
			posted.append("?");
		}
		while (e.hasMoreElements()) {
			if (posted.length() > 1) {
				posted.append("&");
			}
			String curr = (String) e.nextElement();
			posted.append(curr + "=");
			posted.append(request.getParameter(curr));
		}
		int length = posted.length();
		String postedStr = posted.toString();
		if (length == 1 && "?".equals(postedStr)) {
			posted.delete(length - 1, length);
		}
		return posted.toString();
	}

	private String getRemoteAddr(HttpServletRequest request) {
		return WebUtils.getAddr(request);
	}

}
