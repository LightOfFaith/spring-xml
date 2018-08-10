package com.share.lifetime.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitializationDataEvent implements ApplicationListener<ContextRefreshedEvent> {


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("==========event:{}=========", event);
		if (event.getApplicationContext().getParent() == null) {
			log.info("==========数据初始化开始:{}=========", event);
		}

	}


}
