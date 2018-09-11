package com.share.lifetime.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

import com.share.lifetime.validator.annotation.FieldsValueMatchConstraint;

public class FieldsValueMatchConstraintValidator implements ConstraintValidator<FieldsValueMatchConstraint, Object> {

	private String field;
	private String fieldMatch;

	@Override
	public void initialize(FieldsValueMatchConstraint constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);
		Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(fieldMatch);
		if (fieldValue != null) {
			return fieldValue.equals(fieldMatchValue);
		} else {
			return fieldMatchValue == null;
		}
	}

}
