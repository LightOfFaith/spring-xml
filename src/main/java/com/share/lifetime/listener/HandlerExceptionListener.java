package com.share.lifetime.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.share.lifetime.event.ExceptionEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HandlerExceptionListener implements ApplicationListener<ExceptionEvent> {

	@Override
	public void onApplicationEvent(ExceptionEvent event) {
		log.info("处理异常事件。。。。。。");
	}

}
