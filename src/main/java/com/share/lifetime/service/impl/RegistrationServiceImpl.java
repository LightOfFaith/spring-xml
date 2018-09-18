package com.share.lifetime.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.share.lifetime.domain.MailMessage;
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

	public void register(User user) throws Exception {
		sendConfirmationEmail(user);
	}

	private void sendConfirmationEmail(final User user) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"templates/velocity/registration-confirmation.vm", model);
		MailMessage message=new MailMessage();
		message.setSubject("********");
		message.setTo(user.getEmailAddress());
		message.setFrom("307071075@qq.com");
		message.setSentDate(new Date());
		message.setText(text);
		message.setHtml(true);
		emailService.sendMessage(message);
	}

}
