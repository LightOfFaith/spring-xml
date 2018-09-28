package com.share.lifetime.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import com.share.lifetime.util.DateFormatUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationContextClosedListener implements ApplicationListener<ContextClosedEvent> {

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		long startupDate = applicationContext.getStartupDate();
		ApplicationContext parentApplicationContext = applicationContext.getParent();
		String applicationName = applicationContext.getApplicationName();
		String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
		if (parentApplicationContext == null) {// Root ApplicationContext 
			log.info("{} {} ContextClosedEvent Root WebApplicationContext: initialization completed in {}",
					applicationName, activeProfiles,
					DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, startupDate));
			
		} else {//WebApplicationContext
			log.info("{} {} ContextClosedEvent WebApplicationContext initialization completed in {}",
					applicationName, activeProfiles,
					DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, startupDate));
		}
	}

}
