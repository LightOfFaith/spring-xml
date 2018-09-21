package com.share.lifetime.exception;

public abstract class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ErrorCode errCode;

	public BaseException(String message) {
		super(message);
	}

	public BaseException(ErrorCode errCode) {
		this.errCode = errCode;
	}

	public BaseException(String message, ErrorCode errCode) {
		super(message);
		this.setErrCode(errCode);
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
