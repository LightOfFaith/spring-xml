package com.share.lifetime.aop.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface DynamicDataSource {

	/**
	 * 数据源key
	 * @return
	 */
	String value() default "";


	/**
	 * 数据源描述
	 * @return
	 */
	String description() default "";

}
