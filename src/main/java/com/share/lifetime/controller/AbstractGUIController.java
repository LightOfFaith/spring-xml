package com.share.lifetime.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.share.lifetime.base.AjaxResult;
import com.share.lifetime.base.RestApiResult;
import com.share.lifetime.exception.BaseErrorCode;
import com.share.lifetime.exception.BaseException;
import com.share.lifetime.exception.BizException;
import com.share.lifetime.exception.DAOException;
import com.share.lifetime.exception.ErrorCode;
import com.share.lifetime.exception.ParamException;
import com.share.lifetime.exception.RestErrorCode;

public class AbstractGUIController extends AbstractController {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractRestController.class);

	protected static final String LOG_EXCEPTION_TEMPLET1 = "GUI API:[%s],IP:[%s],code:[%s],msg:[%s],Ex:[%s]";

	protected static final String LOG_EXCEPTION_TEMPLET2 = "GUI API:[%s],IP:[%s],code:[%s],msg:[%s],subCode:[%s],subMsg:[%s],Ex:[%s]";


	@Override
	protected <T> AjaxResult<T> success(T result) {
		AjaxResult<T> ajaxResult = new AjaxResult<T>();
		ajaxResult.setCode(SUCCESS);
		ajaxResult.setMsg("");
		ajaxResult.setResult(result);
		return ajaxResult;
	}

	@Override
	protected <T> AjaxResult<T> success(String msg, T result) {
		AjaxResult<T> ajaxResult = new AjaxResult<T>();
		ajaxResult.setCode(SUCCESS);
		ajaxResult.setMsg(msg);
		ajaxResult.setResult(result);
		return null;
	}

	@Override
	protected <T> AjaxResult<T> failure(String msg) {
		AjaxResult<T> ajaxResult = new AjaxResult<T>();
		ajaxResult.setCode(FAILURE);
		ajaxResult.setMsg(msg);
		return ajaxResult;
	}

	@Override
	protected <T> AjaxResult<T> failure(ErrorCode errorCode) {
		AjaxResult<T> ajaxResult = new AjaxResult<T>();
		ajaxResult.setCode(errorCode.getCode());
		ajaxResult.setMsg(errorCode.getMsg());
		return ajaxResult;
	}

	@ExceptionHandler({Exception.class})
	protected @ResponseBody AjaxResult<Object> handleAllException(Exception ex, HttpServletRequest request) {
		ErrorCode errCode = BaseErrorCode.SYS_ERROR;
		AjaxResult<Object> failure = failure(errCode);
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
		String msg = errors.toString();
		BaseErrorCode errCode = BaseErrorCode.PARAM_ERROR;
		AjaxResult<Object> failure = failure(errCode);
		failure.setMsg(msg);
		LOG.info(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request), errCode.getCode(),
				errCode.getMsg(), ex.getClass().getName()));
		LOG.error(ExceptionUtils.getStackTrace(ex));
		return failure;
	}

	@ExceptionHandler({DAOException.class, BizException.class, ParamException.class})
	protected @ResponseBody AjaxResult<Object> handleCustomException(BaseException ex, HttpServletRequest request) {
		String message = ex.getMessage();
		if (StringUtils.isEmpty(message)) {
			ErrorCode errCode = ex.getErrCode();
			if (errCode instanceof RestErrorCode) {
				RestErrorCode restErrorCode = (RestErrorCode) errCode;
				AjaxResult<Object> failure = failure(restErrorCode);
				LOG.info(String.format(LOG_EXCEPTION_TEMPLET2, request.getRequestURL(), getClientIp(request),
						restErrorCode.getCode(), restErrorCode.getMsg(), restErrorCode.getSubCode(),
						restErrorCode.getSubMsg(), ex.getClass().getName()));
				LOG.error(ExceptionUtils.getStackTrace(ex));
				return failure;
			} else {
				AjaxResult<Object> failure = failure(errCode);
				LOG.info(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request),
						errCode.getCode(), errCode.getMsg(), ex.getClass().getName()));
				LOG.error(ExceptionUtils.getStackTrace(ex));
				return failure;
			}
		} else {
			AjaxResult<Object> failure = failure(message);
			String code = failure.getCode();
			String msg = failure.getMsg();
			LOG.info(String.format(LOG_EXCEPTION_TEMPLET1, request.getRequestURL(), getClientIp(request), code, msg,
					ex.getClass().getName()));
			LOG.error(ExceptionUtils.getStackTrace(ex));
			return failure;
		}

	}

}
