package com.share.lifetime.base;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(Include.NON_EMPTY)
public abstract class Result<T> implements Serializable {

	private static final long serialVersionUID = -894571388029744301L;

	protected String code;

	protected String msg;

	protected T result;
	
	protected String timestamp;

}
