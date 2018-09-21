package com.share.lifetime.exception;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = -2053379290800786884L;

	public ServiceException(String message) {
		super(message);
		this.setErrCode(BaseErrorCode.INFRA_ERROR);
	}

	public ServiceException(ErrorCode errCode) {
		super(errCode);
	}

	public ServiceException(String message, ErrorCode errCode) {
		super(message, errCode);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}


}
