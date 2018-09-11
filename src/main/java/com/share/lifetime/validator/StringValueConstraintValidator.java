package com.share.lifetime.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.share.lifetime.validator.annotation.StringValueConstraint;

public class StringValueConstraintValidator implements ConstraintValidator<StringValueConstraint, String> {

	private StringValueConstraint constraintAnnotation;

	@Override
	public void initialize(StringValueConstraint constraintAnnotation) {
		this.constraintAnnotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean required = constraintAnnotation.required();
		String regexp = constraintAnnotation.regexp();
		if (required) {
			if (StringUtils.isNotEmpty(value)) {
				if (StringUtils.isNotEmpty(regexp)) {
					Pattern pattern = Pattern.compile(regexp);
					return pattern.matcher(value).matches();
				}
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}

	}

}
