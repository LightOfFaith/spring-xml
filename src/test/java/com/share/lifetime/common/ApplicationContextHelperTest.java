package com.share.lifetime.common;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration({ "classpath:config/spring.xml" })
@ActiveProfiles("test")
public class ApplicationContextHelperTest {

	@Autowired
	private ApplicationContextHelper applicationContextHelper;

	@Test
	public void testGetApplicationContext() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBeanClassOfT() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBeanString() {
		fail("Not yet implemented");
	}

	@SuppressWarnings("static-access")
	@Test
	public void testGetActiveProfiles() {
		String activeProfiles = applicationContextHelper.getActiveProfiles();
		log.info(activeProfiles);
	}

}
