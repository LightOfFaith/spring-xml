package com.share.lifetime.exception;

public enum BizErrorCode implements RestErrorCode {
	MISSING_SIGNATURE_ERROR(BaseErrorCode.PARAM_ERROR.getCode(), BaseErrorCode.PARAM_ERROR.getMsg(), "1001", "无效签名")

	;


	private BizErrorCode(String code, String msg, String subCode, String subMsg) {
		this.code = code;
		this.msg = msg;
		this.subCode = subCode;
		this.subMsg = subMsg;
	}

	private String code;
	private String msg;
	private String subCode;
	private String subMsg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubMsg() {
		return subMsg;
	}

	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}


}
