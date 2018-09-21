package com.share.lifetime.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.share.lifetime.base.Result;
import com.share.lifetime.exception.ErrorCode;
import com.share.lifetime.util.WebUtils;

public abstract class AbstractController {

	protected static final String SUCCESS = "000000";

	protected static final String FAILURE = "100000";

	protected String getClientIp(HttpServletRequest request) {
		return WebUtils.getAddr(request);
	}

	protected String getStreamAsString(InputStream stream, String charset) throws IOException {
		return WebUtils.getStreamAsString(stream, charset);
	}

	protected <T> boolean isSuccess(Result<T> result) {
		return result != null && SUCCESS.equals(result.getCode());
	}

	protected <T> boolean isFailure(Result<T> result) {
		return result != null && FAILURE.equals(result.getCode());
	}

	protected abstract <T> Result<T> success(T result);

	protected abstract <T> Result<T> success(String msg, T result);

	protected abstract <T> Result<T> failure(String msg);

	protected abstract <T> Result<T> failure(ErrorCode errorCode);


}
