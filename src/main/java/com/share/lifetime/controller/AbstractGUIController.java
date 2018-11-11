package com.share.lifetime.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import org.springframework.web.servlet.ModelAndView;

import com.share.lifetime.base.AjaxResult;
import com.share.lifetime.base.ConfigConsts;
import com.share.lifetime.exception.BaseErrorCode;
import com.share.lifetime.exception.BaseException;
import com.share.lifetime.exception.ErrorCode;
import com.share.lifetime.exception.RestErrorCode;

/**
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
public class AbstractGUIController extends AbstractController {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractGUIController.class);

    public static final String REDIRECT_URL_PREFIX = "redirect:";

    public static final String FORWARD_URL_PREFIX = "forward:";

    protected static final String LOG_EXCEPTION_TEMPLET1 = "GUI API:[%s],IP:[%s],code:[%s],msg:[%s],Ex:[%s]";

    protected static final String LOG_EXCEPTION_TEMPLET2 =
        "GUI API:[%s],IP:[%s],code:[%s],msg:[%s],subCode:[%s],subMsg:[%s],Ex:[%s]";

    @Override
    protected <T> AjaxResult<T> success(T result) {
        return success(null, result);
    }

    @Override
    protected <T> AjaxResult<T> success(String msg, T result) {
        AjaxResult<T> ajaxResult = new AjaxResult<T>();
        ajaxResult.setCode(SUCCESS_CODE);
        ajaxResult.setMsg(SUCCESS);
        ajaxResult.setResult(result);
        ajaxResult.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return ajaxResult;
    }

    @Override
    protected <T> AjaxResult<T> failure(String msg) {
        AjaxResult<T> ajaxResult = new AjaxResult<T>();
        ajaxResult.setCode(FAILURE_CODE);
        ajaxResult.setMsg(FAILURE);
        ajaxResult.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return ajaxResult;
    }

    @Override
    protected <T> AjaxResult<T> failure(ErrorCode errorCode) {
        AjaxResult<T> ajaxResult = new AjaxResult<T>();
        ajaxResult.setCode(errorCode.getCode());
        ajaxResult.setMsg(errorCode.getMsg());
        if (errorCode instanceof RestErrorCode) {
            RestErrorCode restErrorCode = (RestErrorCode)errorCode;
            ajaxResult.setCode(errorCode.getCode() + "," + restErrorCode.getSubCode());
            ajaxResult.setMsg(errorCode.getMsg() + "," + restErrorCode.getSubMsg());
        }
        ajaxResult.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return ajaxResult;
    }

    protected ModelAndView success(String viewName, Map<String, Object> model) {
        ModelAndView mv = new ModelAndView(viewName);
        Set<Entry<String, Object>> entrySet = model.entrySet();
        for (Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            mv.addObject(key, value);
        }
        return mv;
    }

    @ExceptionHandler({Exception.class})
    protected @ResponseBody AjaxResult<Object> handleAllException(Exception ex, HttpServletRequest request) {
        String message = ex.getMessage();
        ErrorCode errCode = BaseErrorCode.SYS_ERROR;
        AjaxResult<Object> failure = failure(errCode);
        ServletContext servletContext = request.getServletContext();
        String initParameter = servletContext.getInitParameter(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME);
        if (ConfigConsts.TEST.equals(initParameter) || ConfigConsts.DEV.equals(initParameter)) {
            failure.setMsg(getDetailsInfo(failure.getMsg(), message));
        }
        LOG.error(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request),
            errCode.getCode(), errCode.getMsg(), ex.getClass().getName()));
        LOG.error(ExceptionUtils.getStackTrace(ex));
        return failure;
    }

    @ExceptionHandler({BindException.class})
    protected @ResponseBody AjaxResult<Object> handleBindException(BindException ex, HttpServletRequest request) {
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
        AjaxResult<Object> failure = failure(errCode);
        failure.setMsg(getDetailsInfo(failure.getMsg(), message));
        LOG.info(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request), errCode.getCode(),
            errCode.getMsg(), ex.getClass().getName()));
        LOG.error(ExceptionUtils.getStackTrace(ex));
        return failure;
    }

    @ExceptionHandler({BaseException.class})
    protected @ResponseBody AjaxResult<Object> handleCustomException(HttpServletRequest request, BaseException ex) {
        String message = ex.getMessage();
        if (StringUtils.isEmpty(message)) {// BaseException(ErrorCode errCode)
            return getFailure(request, ex);
        } else {
            return getFailure(request, ex, message);
        }

    }

    private AjaxResult<Object> getFailure(HttpServletRequest request, BaseException ex) {
        return getFailure(request, ex, null);
    }

    private AjaxResult<Object> getFailure(HttpServletRequest request, BaseException ex, final String reason) {
        ErrorCode errCode = ex.getErrCode();
        if (errCode instanceof BaseErrorCode) {
            BaseErrorCode baseErrorCode = (BaseErrorCode)errCode;
            AjaxResult<Object> failure = failure(baseErrorCode);
            if (!StringUtils.isEmpty(reason)) {
                failure.setMsg(getDetailsInfo(failure.getMsg(), reason));
            }
            LOG.info(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request),
                baseErrorCode.getCode(), baseErrorCode.getMsg(), ex.getClass().getName()));
            LOG.error(ExceptionUtils.getStackTrace(ex));
            return failure;
        } else if (errCode instanceof RestErrorCode) {
            RestErrorCode restErrorCode = (RestErrorCode)errCode;
            AjaxResult<Object> failure = failure(restErrorCode);
            if (!StringUtils.isEmpty(reason)) {
                failure.setMsg(getDetailsInfo(failure.getMsg(), reason));
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
