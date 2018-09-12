package com.share.lifetime.exception;

public enum DAOErrorCode implements RestErrorCode {

	INSERT_ERROR(BaseErrorCode.INFRA_ERROR.getCode(), BaseErrorCode.INFRA_ERROR.getMsg(), "3001", "插入失败"),

	DELETE_ERROR(BaseErrorCode.INFRA_ERROR.getCode(), BaseErrorCode.INFRA_ERROR.getMsg(), "3002", "刪除失败"),

	UPDATE_ERROR(BaseErrorCode.INFRA_ERROR.getCode(), BaseErrorCode.INFRA_ERROR.getMsg(), "3003", "修改失败"),

	SELECT_ERROR(BaseErrorCode.INFRA_ERROR.getCode(), BaseErrorCode.INFRA_ERROR.getMsg(), "3004", "查询失败");


	DAOErrorCode(String code, String msg, String subCode, String subMsg) {
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
