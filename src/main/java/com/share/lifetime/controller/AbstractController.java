package com.share.lifetime.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import com.share.lifetime.base.AbstractResult;
import com.share.lifetime.exception.ErrorCode;
import com.share.lifetime.util.WebUtils;

/**
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
public abstract class AbstractController {

    protected static final String SUCCESS_CODE = "000000";

    protected static final String SUCCESS = "SUCCESS";

    protected static final String FAILURE_CODE = "100000";

    protected static final String FAILURE = "FAILURE";

    protected String getClientIp(HttpServletRequest request) {
        return WebUtils.getAddr(request);
    }

    protected String getStreamAsString(InputStream stream, String charset) throws IOException {
        return WebUtils.getStreamAsString(stream, charset);
    }

    protected <T> boolean isSuccess(AbstractResult<T> result) {
        return result != null && SUCCESS_CODE.equals(result.getCode());
    }

    protected <T> boolean isFailure(AbstractResult<T> result) {
        return result != null && FAILURE_CODE.equals(result.getCode());
    }

    protected abstract <T> AbstractResult<T> success(T result);

    protected abstract <T> AbstractResult<T> success(String msg, T result);

    protected abstract <T> AbstractResult<T> failure(String msg);

    protected abstract <T> AbstractResult<T> failure(ErrorCode errorCode);

}
