package com.share.lifetime.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.share.lifetime.util.LogType;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

	/**
	 * 日志描述
	 * @return
	 */
	String value() default "";
	
	
	/**
	 * 是否忽略日志记录
	 * @return
	 */
	boolean ignore() default false;
	
	/**
	 * 日志打印类型
	 * @return
	 */
	LogType logType() default LogType.WEB;

}
