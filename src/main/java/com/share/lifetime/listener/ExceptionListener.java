package com.share.lifetime.listener;

import java.io.IOException;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.share.lifetime.domain.DingTalkMessage;
import com.share.lifetime.domain.Exception;
import com.share.lifetime.domain.MailMessage;
import com.share.lifetime.domain.Message;
import com.share.lifetime.event.ExceptionEvent;
import com.share.lifetime.service.DingTalkMessageService;
import com.share.lifetime.service.EmailService;
import com.share.lifetime.service.MessageService;
import com.share.lifetime.util.DateFormatUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ExceptionListener implements ApplicationListener<ExceptionEvent> {

	@Async
	@Override
	public void onApplicationEvent(ExceptionEvent event) {
		Object source = event.getSource();
		long timestamp = event.getTimestamp();
		Message message = event.getMessage();
		Exception exception = event.getException();
		MessageService messageService = event.getMessageService();
		log.info("异常监听订阅异常服务信息！！message:{},exception:{}", message, exception);
		if ((message instanceof MailMessage) && (messageService instanceof EmailService)) {
			MailMessage mailMessage = (MailMessage) message;
			EmailService emailService = (EmailService) messageService;
			try {
				emailService.sendMessage(mailMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if ((message instanceof DingTalkMessage) && messageService instanceof DingTalkMessageService) {
			DingTalkMessage dingTalkMessage = (DingTalkMessage) message;
			DingTalkMessageService dingTalkMessageService = (DingTalkMessageService) messageService;
			try {
				dingTalkMessageService.sendMessage(dingTalkMessage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("异常监听订阅异常服务信息处理完成！！source:{},Date:{}", source,
				DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, timestamp));
	}

}
