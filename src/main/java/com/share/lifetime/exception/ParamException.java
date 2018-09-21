package com.share.lifetime.exception;

public class ParamException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ParamException(String message) {
		super(message);
		this.setErrCode(BaseErrorCode.PARAM_ERROR);
	}

	public ParamException(ErrorCode errCode) {
		super(errCode);
	}

	public ParamException(String message, ErrorCode errCode) {
		super(message, errCode);
	}

	public ParamException(String message, Throwable cause) {
		super(message, cause);
	}

}
