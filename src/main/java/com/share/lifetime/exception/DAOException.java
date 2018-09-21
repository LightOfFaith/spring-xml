package com.share.lifetime.exception;

public class DAOException extends BaseException {

	private static final long serialVersionUID = 1L;

	public DAOException(String message) {
		super(message);
		this.setErrCode(BaseErrorCode.INFRA_ERROR);
	}

	public DAOException(ErrorCode errCode) {
		super(errCode);
	}

	public DAOException(String message, ErrorCode errCode) {
		super(message, errCode);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
