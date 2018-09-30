package com.share.lifetime.domain;

import com.share.lifetime.exception.ErrorCode;
import com.share.lifetime.exception.RestErrorCode;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Exception {

	/**
	 * 异常实例
	 */
	private String instance;

	/**
	 * 异常触发条件
	 */
	private String condition;

	/**
	 * 错误码
	 */
	private String code;

	private String msg;

	private String subCode;

	private String subMsg;

	private ErrorCode errorCode;

	/**
	 * 异常项目
	 */
	private String project;

	/**
	 *   异常发生时间
	 */
	private String time;

	/**
	 * 备注
	 */
	private String remark;

	public Exception(String instance, String condition, ErrorCode errorCode, String project, String time) {
		super();
		this.instance = instance;
		this.condition = condition;
		this.errorCode = errorCode;
		this.code = this.errorCode.getCode();
		this.msg = this.errorCode.getMsg();
		if (this.errorCode instanceof RestErrorCode) {
			RestErrorCode restErrorCode = (RestErrorCode) this.errorCode;
			this.subCode = restErrorCode.getSubCode();
			this.subMsg = restErrorCode.getSubMsg();
		}
		this.project = project;
		this.time = time;
	}


}
