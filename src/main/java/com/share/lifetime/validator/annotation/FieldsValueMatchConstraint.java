package com.share.lifetime.validator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

import com.share.lifetime.validator.FieldsValueMatchConstraintValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = FieldsValueMatchConstraintValidator.class)
public @interface FieldsValueMatchConstraint {

	String message() default "Fields values don't match!";

	String field();

	String fieldMatch();

	@Target({ElementType.TYPE})
	@Retention(RetentionPolicy.RUNTIME)
	@interface List {
		FieldsValueMatchConstraint[] value();
	}

}
