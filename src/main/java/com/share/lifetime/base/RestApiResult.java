package com.share.lifetime.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonPropertyOrder(value = {"code", "msg", "sub_code", "sub_msg", "url", "timestamp"})
public class RestApiResult<T> extends ApiResult<T> {

	private static final long serialVersionUID = 1L;

	@JsonProperty(value = "sub_code")
	protected String subCode;

	@JsonProperty(value = "sub_msg")
	protected String subMsg;

	protected String url;


}
