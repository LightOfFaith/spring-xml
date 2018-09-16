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
import com.share.lifetime.service.RegistrationService;


@SuppressWarnings("deprecation")
@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;

	public void register(User user) {
		sendConfirmationEmail(user);
	}

	private void sendConfirmationEmail(final User user) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setSubject("********");
				message.setTo(user.getEmailAddress());
				message.setFrom("307071075@qq.com");
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("user", user);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						"templates/velocity/registration-confirmation.vm", model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
	}

}
