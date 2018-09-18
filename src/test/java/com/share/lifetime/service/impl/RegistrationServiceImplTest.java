package com.share.lifetime.service.impl;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.share.lifetime.domain.User;
import com.share.lifetime.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring.xml"})
@ActiveProfiles("test")
public class RegistrationServiceImplTest {

	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private VelocityEngine velocityEngine;

	@Test
	public void testRegister() throws Exception {
		log.info(velocityEngine.getProperty("class.resource.loader.class").toString());
		User user = new User();
		user.setUsername("Thomas");
		user.setEmailAddress("381345579@qq.com");
		registrationService.register(user);
	}

}
