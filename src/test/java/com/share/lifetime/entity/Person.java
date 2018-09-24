package com.share.lifetime.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

	private String name;

	private Boolean sex;// true 男，false 女

	private Integer age;

	private Date birthday;// 生日

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

}
