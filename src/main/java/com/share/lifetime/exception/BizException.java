package com.share.lifetime.exception;

public class BizException extends BaseException {

	private static final long serialVersionUID = 1648488500365747904L;

	public BizException(String message) {
		super(message);
		this.setErrCode(BaseErrorCode.BIZ_ERROR);
	}

	public BizException(ErrorCode errCode) {
		super(errCode);
	}

	public BizException(String message, ErrorCode errCode) {
		super(message, errCode);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

}
