package com.share.lifetime.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.share.lifetime.util.DateFormatUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InitializationDataEvent implements ApplicationListener<ContextRefreshedEvent> {


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		ApplicationContext parentApplicationContext = applicationContext.getParent();
		Object source = event.getSource();
		long timestamp = event.getTimestamp();
		log.info("ContextRefreshedEvent ApplicationContext:{},source:{},time:{}", applicationContext, source,
				DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, timestamp));
		String id = applicationContext.getId();
		String applicationName = applicationContext.getApplicationName();
		log.info("ContextRefreshedEvent id:{},applicationName:{}", id, applicationName);
		String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
		log.info("ContextRefreshedEvent activeProfiles:{},length:{}", activeProfiles, activeProfiles.length);
		if (parentApplicationContext == null) {
			// Root ApplicationContext
			log.info("ContextRefreshedEvent parentApplicationContext:{}", parentApplicationContext);
		} else {

		}

	}


}
