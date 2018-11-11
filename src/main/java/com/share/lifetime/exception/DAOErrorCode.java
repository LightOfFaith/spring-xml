package com.share.lifetime.exception;

public enum DAOErrorCode implements RestErrorCode {

	INSERT_ERROR(BaseErrorCode.INFRA_ERROR.getCode(),BaseErrorCode.INFRA_ERROR.getMsg(),"3001","服务暂不可用，稍后重试"),
	
	DELETE_ERROR(BaseErrorCode.INFRA_ERROR.getCode(),BaseErrorCode.INFRA_ERROR.getMsg(),"3002","服务暂不可用，稍后重试"),
	
	UPDATE_ERROR(BaseErrorCode.INFRA_ERROR.getCode(),BaseErrorCode.INFRA_ERROR.getMsg(),"3003","服务暂不可用，稍后重试"),
	
	SELECT_ERROR(BaseErrorCode.INFRA_ERROR.getCode(),BaseErrorCode.INFRA_ERROR.getMsg(),"3004","服务暂不可用，稍后重试");
	

	DAOErrorCode(String code, String msg, String subCode, String subMsg) {
		this.subCode = subCode;
		this.subMsg = subMsg;
	}

	private String code;
	private String msg;
	private String subCode;
	private String subMsg;

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

	@Override
	public String getSubCode() {
		return subCode;
	}

	@Override
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	@Override
	public String getSubMsg() {
		return subMsg;
	}

	@Override
	public void setSubMsg(String subMsg) {
		this.subMsg = subMsg;
	}


}
