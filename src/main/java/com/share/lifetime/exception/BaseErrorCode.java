package com.share.lifetime.exception;

/**
 *  参数错误：1000 - 1999 业务错误：2000 - 2999 基础错误：3000 - 3999 系统错误：4000 - 4999
 * 
 * @author liaoxiang
 *
 */
public enum BaseErrorCode implements ErrorCode {

	PARAM_ERROR("1000", "参数校验错误"), BIZ_ERROR("2000", "业务逻辑错误"),
	/**
	 * (数据库，缓存，消息等)
	 */
	INFRA_ERROR("3000", "基础设施错误"), SYS_ERROR("4000", "未知系统错误");


	BaseErrorCode(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private String code;

	private String msg;

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public void setMsg(String msg) {
		this.msg = msg;
	}


}
