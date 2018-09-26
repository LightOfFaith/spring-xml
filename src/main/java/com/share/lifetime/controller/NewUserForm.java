package com.share.lifetime.controller;

import com.share.lifetime.validator.annotation.StringValueConstraint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewUserForm {
	private String email;
	@StringValueConstraint(regexp = "^[0]6$")
	private String verifyEmail;
	@StringValueConstraint(regexp = "^[0]6$")
	private String password;
	private String verifyPassword;


}