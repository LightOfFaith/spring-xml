package com.share.lifetime.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCode errCode;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorCode getErrCode() {
		return errCode;
	}

	public void setErrCode(ErrorCode errCode) {
		this.errCode = errCode;
	}


}
