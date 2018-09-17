package com.share.lifetime.service.impl;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.share.lifetime.domain.Exception;
import com.share.lifetime.domain.Message;
import com.share.lifetime.event.ExceptionEvent;
import com.share.lifetime.service.ExceptionService;
import com.share.lifetime.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("exceptionService")
public class ExceptionServiceImpl implements ExceptionService, ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void handlerException(Message message, Exception exception, MessageService messageService) {
		log.info("异常服务发布信息！！Message:{},Exception:{}", message, exception);
		ExceptionEvent event = new ExceptionEvent(this, message, exception, messageService);
		applicationEventPublisher.publishEvent(event);
	}


}
