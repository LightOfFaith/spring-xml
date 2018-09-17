package com.share.lifetime.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.share.lifetime.domain.User;
import com.share.lifetime.service.EmailService;
import com.share.lifetime.service.RegistrationService;


@SuppressWarnings("deprecation")
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private EmailService emailService;
	@Autowired
	private VelocityEngine velocityEngine;

	public void register(User user) {
		sendConfirmationEmail(user);
	}

	private void sendConfirmationEmail(final User user) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"templates/velocity/registration-confirmation.vm", model);
		emailService.sendMessage("********", user.getEmailAddress(), "307071075@qq.com", content);
	}

}
