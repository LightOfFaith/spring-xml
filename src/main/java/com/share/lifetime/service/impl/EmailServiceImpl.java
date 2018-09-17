package com.share.lifetime.service.impl;

import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.share.lifetime.service.EmailService;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMessage(String subject, String to, String from, String content) {
		sendMessage(subject, to, from, new Date(), content, false);
	}

	@Override
	public void sendMessage(String subject, String to, String from, Date sentDate, String content) {
		sendMessage(subject, to, from, sentDate, content, false);
	}

	public void sendMessage(String subject, String to, String from, Date sentDate, String content, boolean html) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setSubject(subject);
				message.setTo(to);
				message.setFrom(from);
				message.setSentDate(sentDate);
				message.setText(content, html);
			}
		};
		mailSender.send(preparator);
	}

}
