package com.share.lifetime.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.share.lifetime.util.DateFormatUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Spring容器初始化或刷新触发事件
 * @author liaoxiang
 *
 */
@Slf4j
public class ApplicationContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		long startupDate = applicationContext.getStartupDate();
		ApplicationContext parentApplicationContext = applicationContext.getParent();
		String applicationName = applicationContext.getApplicationName();
		String[] activeProfiles = applicationContext.getEnvironment().getActiveProfiles();
		if (parentApplicationContext == null) {// Root ApplicationContext 
			log.info("{} {} ContextRefreshedEvent Root WebApplicationContext: initialization completed in {}",
					applicationName, activeProfiles,
					DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, startupDate));
			
		} else {//WebApplicationContext
			log.info("{} {} ContextRefreshedEvent WebApplicationContext initialization completed in {}",
					applicationName, activeProfiles,
					DateFormatUtils.formatDate(DateFormatUtils.PATTERN_DEFAULT_ON_SECOND, startupDate));
		}
	}


}
