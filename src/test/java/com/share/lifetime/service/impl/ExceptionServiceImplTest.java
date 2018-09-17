package com.share.lifetime.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.share.lifetime.domain.Exception;
import com.share.lifetime.exception.BaseErrorCode;
import com.share.lifetime.exception.ErrorCode;
import com.share.lifetime.service.ExceptionService;
import com.share.lifetime.util.DateFormatUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring.xml"})
@ActiveProfiles("test")
public class ExceptionServiceImplTest {

	@Autowired
	private ExceptionService exceptionService;

	@Test
	public void testHandlerException() {
		Exception exception = new Exception();
		exception.setInstance("192.168.0.1");
		ErrorCode errorCode = BaseErrorCode.BIZ_ERROR;
		exception.setErrorCode(errorCode);
		exception.setTimestamp(DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, new Date()));
		exceptionService.handlerException(exception);
	}

}
