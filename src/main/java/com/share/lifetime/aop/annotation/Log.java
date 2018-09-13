package com.share.lifetime.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.share.lifetime.util.LogType;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

	/**
	 * API name
	 * @return
	 */
	String name() default "";

	/**
	 * ALLOWED TYPE
	 * @return
	 */
	LogType logType() default LogType.WEB;

}
