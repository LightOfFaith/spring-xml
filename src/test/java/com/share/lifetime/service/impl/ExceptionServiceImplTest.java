package com.share.lifetime.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.share.lifetime.domain.DingTalkMessage;
import com.share.lifetime.domain.Exception;
import com.share.lifetime.domain.MailMessage;
import com.share.lifetime.exception.BaseErrorCode;
import com.share.lifetime.exception.BizErrorCode;
import com.share.lifetime.exception.ErrorCode;
import com.share.lifetime.service.DingTalkMessageService;
import com.share.lifetime.service.EmailService;
import com.share.lifetime.service.ExceptionService;
import com.share.lifetime.util.DateFormatUtils;
import com.share.lifetime.util.MessageTemplateUtils;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring.xml"})
@ActiveProfiles("test")
public class ExceptionServiceImplTest {

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Autowired
	private ExceptionService exceptionService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private DingTalkMessageService dingTalkMessageService;

	@Test
	public void testHandlerExceptionByBaseErrorCode() throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		BaseErrorCode errorCode = BaseErrorCode.BIZ_ERROR;
		handlerExceptionToEmail(errorCode);
	}

	@Test
	public void testHandlerExceptionByRestErrorCode() throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		BizErrorCode errorCode = BizErrorCode.MISSING_SIGNATURE_ERROR;
		handlerExceptionToDingTalk(errorCode);
//		handlerExceptionToEmail(errorCode);
	}

	private void handlerExceptionToDingTalk(ErrorCode errorCode) {
		DingTalkMessage message = new DingTalkMessage();
		message.setSubject("********");
		message.setTo("381345579@qq.com");
		message.setFrom("307071075@qq.com");
		Exception exception = new Exception("192.168.0.1", "未配置子商户费率！！请即使查阅重新提交！！", errorCode, "Pay",
				DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, new Date()));
		String processTemplateIntoString = MessageTemplateUtils.getExceptionToTemplate(exception);
		message.setText(processTemplateIntoString);
		message.setSentDate(new Date());
		exceptionService.handlerException(message, exception, dingTalkMessageService);
	}

	private void handlerExceptionToEmail(ErrorCode errorCode) throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException, TemplateException {
		MailMessage message = new MailMessage();
		message.setSubject("********");
		message.setTo("381345579@qq.com");
		message.setFrom("307071075@qq.com");
		Exception exception = new Exception("192.168.0.1", "未配置子商户费率！！请即使查阅重新提交！！", errorCode, "Pay",
				DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, new Date()));
		String processTemplateIntoString = getTemplate(exception);
		message.setText(processTemplateIntoString);
		message.setSentDate(new Date());
		message.setHtml(true);
		exceptionService.handlerException(message, exception, emailService);
	}

	private String getTemplate(Exception exception) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException {
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		Template template = configuration.getTemplate("exception.ftl");
		Map<String, Object> model = new HashMap<>();
		model.put("exception", exception);
		String processTemplateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		return processTemplateIntoString;
	}

}
