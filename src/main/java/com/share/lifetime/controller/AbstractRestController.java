package com.share.lifetime.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.share.lifetime.base.ConfigConsts;
import com.share.lifetime.base.RestApiResult;
import com.share.lifetime.exception.BaseErrorCode;
import com.share.lifetime.exception.BaseException;
import com.share.lifetime.exception.ErrorCode;
import com.share.lifetime.exception.RestErrorCode;

/**
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
public class AbstractRestController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractRestController.class);

    protected static final String LOG_EXCEPTION_TEMPLET1 = "REST API:[%s],IP:[%s],code:[%s],msg:[%s],Ex:[%s]";

    protected static final String LOG_EXCEPTION_TEMPLET2 =
        "REST API:[%s],IP:[%s],code:[%s],msg:[%s],subCode:[%s],subMsg:[%s],Ex:[%s]";

    @Override
    protected <T> RestApiResult<T> success(T result) {
        return success(null, result);
    }

    @Override
    protected <T> RestApiResult<T> success(String msg, T result) {
        RestApiResult<T> apiResult = new RestApiResult<>();
        apiResult.setCode(SUCCESS_CODE);
        apiResult.setMsg(SUCCESS);
        apiResult.setSubCode(SUCCESS_CODE);
        apiResult.setSubMsg(msg);
        apiResult.setResult(result);
        apiResult.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return apiResult;
    }

    @Override
    protected <T> RestApiResult<T> failure(String msg) {
        RestApiResult<T> apiResult = new RestApiResult<>();
        apiResult.setCode(SUCCESS_CODE);
        apiResult.setMsg(SUCCESS);
        apiResult.setSubCode(FAILURE_CODE);
        apiResult.setSubMsg(msg);
        apiResult.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return apiResult;
    }

    @Override
    protected <T> RestApiResult<T> failure(ErrorCode errorCode) {
        RestApiResult<T> apiResult = new RestApiResult<>();
        apiResult.setCode(errorCode.getCode());
        apiResult.setMsg(errorCode.getMsg());
        if (errorCode instanceof RestErrorCode) {
            RestErrorCode restErrorCode = (RestErrorCode)errorCode;
            apiResult.setSubCode(restErrorCode.getSubCode());
            apiResult.setSubMsg(restErrorCode.getSubMsg());
        }
        apiResult.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return apiResult;
    }

    protected <T> RestApiResult<T> failure(String subCode, String subMsg) {
        RestApiResult<T> apiResult = new RestApiResult<>();
        apiResult.setCode(FAILURE_CODE);
        apiResult.setMsg(FAILURE);
        apiResult.setSubCode(subCode);
        apiResult.setSubMsg(subMsg);
        apiResult.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return apiResult;
    }

    @ExceptionHandler({Exception.class})
    protected @ResponseBody RestApiResult<Object> handleAllException(HttpServletRequest request, Exception ex) {
        String message = ex.getMessage();
        ErrorCode errCode = BaseErrorCode.SYS_ERROR;
        RestApiResult<Object> failure = failure(errCode);
        ServletContext servletContext = request.getServletContext();
        String initParameter = servletContext.getInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME);
        if (ConfigConsts.TEST.equals(initParameter) || ConfigConsts.DEV.equals(initParameter)) {
            failure.setSubMsg(getDetailsInfo(failure.getSubMsg(), message));
        }
        LOG.error(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request),
            errCode.getCode(), errCode.getMsg(), ex.getClass().getName()));
        LOG.error(ExceptionUtils.getStackTrace(ex));
        return failure;
    }

    @ExceptionHandler({BindException.class})
    protected @ResponseBody RestApiResult<Object> handleBindException(BindException ex, HttpServletRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        StringBuilder errors = new StringBuilder();
        for (FieldError error : fieldErrors) {
            errors.append("[" + error.getField() + ": " + error.getDefaultMessage() + "],");
        }
        for (ObjectError objectError : globalErrors) {
            errors.append("[" + objectError.getObjectName() + ": " + objectError.getDefaultMessage() + "],");
        }
        int length = errors.length();
        if (length > 0) {
            int start = length - 1;
            int end = length;
            errors.delete(start, end);
        }
        String message = errors.toString();
        BaseErrorCode errCode = BaseErrorCode.PARAM_ERROR;
        RestApiResult<Object> failure = failure(errCode);
        failure.setSubCode(errCode.getCode());
        failure.setSubMsg(getDetailsInfo(failure.getSubMsg(), message));
        LOG.info(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request), errCode.getCode(),
            errCode.getMsg(), ex.getClass().getName()));
        LOG.error(ExceptionUtils.getStackTrace(ex));
        return failure;
    }

    @ExceptionHandler({BaseException.class})
    protected @ResponseBody RestApiResult<Object> handleCustomException(HttpServletRequest request, BaseException ex) {
        String message = ex.getMessage();
        if (StringUtils.isEmpty(message)) {// BaseException(ErrorCode errCode)
            return getFailure(request, ex);
        } else {
            return getFailure(request, ex, message);
        }

    }

    private RestApiResult<Object> getFailure(HttpServletRequest request, BaseException ex) {
        return getFailure(request, ex, null);
    }

    private RestApiResult<Object> getFailure(HttpServletRequest request, BaseException ex, final String detailsInfo) {
        ErrorCode errCode = ex.getErrCode();
        if (errCode instanceof BaseErrorCode) {
            BaseErrorCode baseErrorCode = (BaseErrorCode)errCode;
            RestApiResult<Object> failure = failure(baseErrorCode);
            if (!StringUtils.isEmpty(detailsInfo)) {
                failure.setSubMsg(getDetailsInfo(failure.getSubMsg(), detailsInfo));
            }
            LOG.info(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request),
                baseErrorCode.getCode(), baseErrorCode.getMsg(), ex.getClass().getName()));
            LOG.error(ExceptionUtils.getStackTrace(ex));
            return failure;
        } else if (errCode instanceof RestErrorCode) {
            RestErrorCode restErrorCode = (RestErrorCode)errCode;
            RestApiResult<Object> failure = failure(restErrorCode);
            if (!StringUtils.isEmpty(detailsInfo)) {
                failure.setSubMsg(getDetailsInfo(failure.getSubMsg(), detailsInfo));
            }
            LOG.info(String.format(LOG_EXCEPTION_TEMPLET2, request.getRequestURL(), getClientIp(request),
                restErrorCode.getCode(), restErrorCode.getMsg(), restErrorCode.getSubCode(), restErrorCode.getSubMsg(),
                ex.getClass().getName()));
            LOG.error(ExceptionUtils.getStackTrace(ex));
            return failure;
        } else {
            return null;
        }
    }

    private String getDetailsInfo(final String msg, final String detailsInfo) {
        boolean flag = false;
        StringBuilder builder = new StringBuilder(4);
        if (!StringUtils.isEmpty(msg)) {
            builder.append(msg);
            flag = true;
        }
        if (flag) {
            if (!StringUtils.isEmpty(detailsInfo)) {
                builder.append("(").append(detailsInfo).append(")");
            }
        } else {
            builder.append(detailsInfo);

        }
        return builder.toString();
    }

}
