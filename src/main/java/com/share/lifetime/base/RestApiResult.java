package com.share.lifetime.base;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestApiResult<T> extends ApiResult<T> {

	private static final long serialVersionUID = 1L;

	@JsonSetter(value = "sub_code")
	protected String subCode;

	@JsonSetter(value = "sub_msg")
	protected String subMsg;

	protected String url;


}
