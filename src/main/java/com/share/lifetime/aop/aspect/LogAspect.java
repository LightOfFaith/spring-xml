package com.share.lifetime.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.share.lifetime.aop.annotation.Log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
public class LogAspect {

	private static final String UNKNOWN = "Unkown";

	private static final String WEB_LOG_TEMPLATE = "WEB API:[%s] path:[%s] is called by User:[%s] with IP:[%s], result is %s.";

	private static final String REST_LOG_TEMPLATE = "REST API:[%s] path:[%s] is called by IP:[%s], result is %s.";

	@Around("@annotation(log)")
	public Object log(ProceedingJoinPoint joinPoint, Log logflag) {

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();


		String methodName = signature.getName();//
		
		Object[] args = joinPoint.getArgs();
		
		Boolean isSuccess = Boolean.FALSE;

		try {
			Object result = joinPoint.proceed();
			isSuccess = Boolean.TRUE;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return joinPoint;
	}


}
