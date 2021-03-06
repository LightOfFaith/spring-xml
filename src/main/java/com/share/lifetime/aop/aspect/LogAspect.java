package com.share.lifetime.aop.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.share.lifetime.aop.annotation.Log;
import com.share.lifetime.aop.annotation.LogParam;
import com.share.lifetime.base.SessionAttributeKeys;
import com.share.lifetime.util.LogContext;
import com.share.lifetime.util.LogType;
import com.share.lifetime.util.MapUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Aspect
public class LogAspect {

	private static final String UNKNOWN = "Unkown";

	private static final String WEB_LOG_TEMPLATE = "WEB API:[%s] path:[%s] is called by User:[%s] with IP:[%s], result is %s.\nresult:[%s]";

	private static final String REST_LOG_TEMPLATE = "REST API:[%s] path:[%s] is called by IP:[%s], result is %s.\nresult:[%s]";

	// @Around("@annotation(logAnno)")
	public Object log(ProceedingJoinPoint joinPoint, Log logAnno) throws Throwable {
		addLogParamsIfPossible(joinPoint);
		Boolean isSuccess = Boolean.FALSE;
		String methodName = getMethodName(joinPoint, logAnno);
		Object result = null;
		try {
			result = joinPoint.proceed();
			isSuccess = Boolean.TRUE;
			return result;
		} finally {
			log(isSuccess, logAnno.logType(), methodName, JSONObject.toJSONString(result));
			LogContext.reset();
		}
	}

	private String getMethodName(ProceedingJoinPoint joinPoint, Log logAnno) {
		if (StringUtils.isNotBlank(logAnno.name())) {
			return logAnno.name();
		}
		Signature signature = joinPoint.getSignature();
		return signature.getName();
	}

	private void addLogParamsIfPossible(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		if (signature instanceof MethodSignature) {
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();
			Annotation[][] annotations = method.getParameterAnnotations();
			Object[] args = joinPoint.getArgs();
			Class<? extends Annotation> annotationType;
			int i = 0;
			for (Object arg : args) {
				for (Annotation annotation : annotations[i]) {
					annotationType = annotation.annotationType();
					if (LogParam.class == annotationType) {
						LogParam logParamAnno = (LogParam) annotation;
						String value = logParamAnno.value();
						if (value != null) {
							LogContext.put(value, arg == null ? null : arg.toString());
						}
					}
				}
				i++;
			}
		}

	}

	private void log(Boolean isSuccess, LogType logType, String methodName, String result) {
		String content = null;
		switch (logType) {
			case REST:
				content = getRESTRequstContent(methodName, isSuccess, result);
				break;
			case WEB:
			default:
				content = getWebRequestContent(methodName, isSuccess, result);
		}
		log.info(content);
	}

	protected String getWebRequestContent(String name, Boolean isSuccess, String result) {
		String apiName = StringUtils.isNotBlank(name) ? name : "";
		return buildLogContent(String.format(WEB_LOG_TEMPLATE, apiName, getURL(), getUserName(), getIpAddress(),
				getResultValue(isSuccess), result));
	}

	protected String getRESTRequstContent(String name, Boolean isSuccess, String result) {
		String apiName = StringUtils.isNotBlank(name) ? name : "";
		return buildLogContent(
				String.format(REST_LOG_TEMPLATE, apiName, getURL(), getIpAddress(), getResultValue(isSuccess), result));
	}

	private String buildLogContent(String initValue) {
		StringBuilder stringBuilder = new StringBuilder(initValue);
		Map<String, String> logContextMap = LogContext.currentLogContext();
		if (MapUtils.isNotEmpty(logContextMap)) {
			stringBuilder.append(" Log Context info:").append(logContextMap).append(".");
		}

		return stringBuilder.toString();
	}

	private String getURL() {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return StringUtils.EMPTY;
		}
		StringBuffer requestURL = request.getRequestURL();
		return requestURL.toString();
	}

	private String getIpAddress() {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return StringUtils.EMPTY;
		}

		String ip = request.getHeader("X-FORWARDED-FOR");
		return StringUtils.isBlank(ip) ? request.getRemoteAddr() : ip;
	}

	private String getUserName() {
		HttpServletRequest request = getRequest();
		if (request == null) {
			return UNKNOWN;
		}

		String loginUser = (String) request.getSession().getAttribute(SessionAttributeKeys.LOGIN_USER_NAME);
		return StringUtils.isBlank(loginUser) ? UNKNOWN : loginUser;
	}

	private String getResultValue(boolean isSuccess) {
		return isSuccess ? "success" : "failed";
	}

	private HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

}
