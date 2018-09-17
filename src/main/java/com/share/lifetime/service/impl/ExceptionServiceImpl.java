package com.share.lifetime.service.impl;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.share.lifetime.event.ExceptionEvent;
import com.share.lifetime.service.ExceptionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("exceptionService")
public class ExceptionServiceImpl implements ExceptionService, ApplicationEventPublisherAware {

	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public void handlerException(com.share.lifetime.domain.Exception exception) {
		ApplicationEvent event = new ExceptionEvent(this, exception);
		log.info(exception.toString());
		applicationEventPublisher.publishEvent(event);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

}
