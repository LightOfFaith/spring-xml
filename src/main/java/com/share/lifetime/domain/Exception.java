package com.share.lifetime.domain;

import com.share.lifetime.exception.ErrorCode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Exception {

	/**
	 * 异常实例
	 */
	private String instance;
	
	/**
	 * 错误码
	 */
	private ErrorCode errorCode;

	/**
	 *   异常发生时间
	 */
	private String timestamp;

}
