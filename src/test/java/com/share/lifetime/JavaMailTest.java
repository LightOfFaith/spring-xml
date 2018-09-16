package com.share.lifetime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author liaoxiang
 * @see https://docs.spring.io/spring/docs/4.3.19.RELEASE/spring-framework-reference/htmlsingle/#mail
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring.xml"})
@ActiveProfiles("test")
public class JavaMailTest {

	@Autowired
	@Qualifier(value = "mailSender")
	private JavaMailSender mailSender;

	@Test
	public void test() {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo("381345579@qq.com"); // 收信人邮箱
		mailMessage.setFrom("307071075@qq.com"); // 发送人邮箱
		mailMessage.setSubject("测试"); // 邮件主题
		mailMessage.setText("测试"); // 邮件内容

		mailSender.send(mailMessage);
	}

}
