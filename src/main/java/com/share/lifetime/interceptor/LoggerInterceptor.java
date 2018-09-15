package com.share.lifetime.interceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.share.lifetime.util.DateFormatUtils;
import com.share.lifetime.util.MapUtils;
import com.share.lifetime.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private static final ThreadLocal<Long> START_TIME = new NamedThreadLocal<Long>("Start Time");

	/**
	 * 在执行实际处理程序之前调用，但尚未生成视图 该方法返回一个布尔值 
	 * 它告诉Spring请求是否应由处理程序进一步处理（true）或不处理（false）。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Long startTime = System.currentTimeMillis();
		START_TIME.set(startTime);
		if (handler instanceof HandlerMethod) {
			StringBuilder builder = new StringBuilder();
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			builder.append("\n------------------------Start Time:")
					.append(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, new Date()))
					.append("------------------------\n");
			builder.append("Controller     :").append(handlerMethod.getBean().getClass().getName()).append("\n");
			builder.append("Method         :").append(handlerMethod.getMethod().getName()).append("\n");
			builder.append("Request Method :").append(request.getMethod()).append("\n");
			builder.append("ContentType    :").append(request.getContentType()).append("\n");
			builder.append("ContentLength  :").append(request.getContentLength()).append("\n");
			builder.append("Charset        :").append(request.getCharacterEncoding()).append("\n");
			builder.append("URL            :").append(request.getRequestURL()).append("\n");
			builder.append("IP             :").append(WebUtils.getAddr(request)).append("\n");
			builder.append("ParameterValues:").append(getParameter2String(request)).append("\n");
			// builder.append("User :").append().append("\n");
			log.info(builder.toString());
		}
		return true;
	}

	

	/**
	 * 在执行处理程序后调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Long elapsedTime = getElapsedTime();
		StringBuilder builder = new StringBuilder();
		builder.append("\n------------------------Elapsed Time   :").append(elapsedTime).append("ms")
				.append("------------------------\n");
	}

	/**
	 * 在HandlerAdapter处理请求之后，但在生成视图之前立即调用此方法。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
		Long elapsedTime = getElapsedTime();

		if (ex != null) {
			log.error(ExceptionUtils.getStackTrace(ex));
		}
		StringBuilder builder = new StringBuilder();
		builder.append("\n------------------------End Time:")
				.append(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, new Date()))
				.append("------------------------\n");
		builder.append("耗时:").append(elapsedTime).append("ms,").append("URL:").append(WebUtils.getAddr(request))
				.append("\n");
		log.info(builder.toString());
		builder.append("最大内存:").append(Runtime.getRuntime().maxMemory() / 1024 / 1024).append("mb,").append("已分配内存:")
				.append(Runtime.getRuntime().totalMemory() / 1024 / 1024).append("mb,").append("已分配内存中的剩余空间:")
				.append(Runtime.getRuntime().freeMemory() / 1024 / 1024).append("mb,").append("最大可用内存:")
				.append((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()
						+ Runtime.getRuntime().freeMemory()) / 1024 / 1024)
				.append("mb\n");
		log.debug(builder.toString());
		START_TIME.remove();

	}
	
	/**
	 * 耗时
	 * @return
	 */
	private Long getElapsedTime() {
		Long startTime = START_TIME.get();
		Long endTime = System.currentTimeMillis();
		Long elapsedTime = endTime - startTime;
		return elapsedTime;
	}
	
	private String getParameter2String(HttpServletRequest request) throws IOException {
		String method = request.getMethod();
		String contentType = request.getContentType();
		String charset = request.getCharacterEncoding();
		if (("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method))
				&& (contentType.contains(MediaType.APPLICATION_JSON_VALUE)
						|| contentType.contains(MediaType.TEXT_PLAIN_VALUE))) {
			// HttpServletRequestBodyWrapper requestBodyWrapper = new HttpServletRequestBodyWrapper(request);
			// InputStream stream = requestBodyWrapper.getInputStream();
			ContentCachingRequestWrapper requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
			InputStream stream = requestCacheWrapperObject.getInputStream();
			return getStreamAsString(stream, charset);
		} else {
			Map<String, String[]> parameterMap = request.getParameterMap();
			return MapUtils.mapToString(parameterMap);
		}
	}

	private String getStreamAsString(InputStream stream, String charset) throws IOException {
		BufferedReader reader = null;
		InputStreamReader in = null;
		try {
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
			return writer.toString();
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

}
