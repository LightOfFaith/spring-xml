package com.share.lifetime.validator.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.share.lifetime.validator.StringValueConstraintValidator;

@Documented
@Retention(RUNTIME)
@Target({TYPE, FIELD})
@Constraint(validatedBy = StringValueConstraintValidator.class)
public @interface StringValueConstraint {

	String message() default "Invalid Fields values!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	boolean required() default true;

	/**
	 * 正则表达式
	 */
	String regexp() default "";

	/**
	 * 字段值必填
	 * 
	 */

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		StringValueConstraint[] value();
	}

}
