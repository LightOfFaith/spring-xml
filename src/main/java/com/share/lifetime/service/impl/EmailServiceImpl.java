package com.share.lifetime.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.share.lifetime.domain.MailMessage;
import com.share.lifetime.domain.AbstractMessage;
import com.share.lifetime.service.EmailService;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public void sendMessage(AbstractMessage message) {
		if (message instanceof MailMessage) {
			MailMessage mailMessage = (MailMessage) message;
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				@Override
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
					message.setSubject(mailMessage.getSubject());
					message.setTo(mailMessage.getTo());
					message.setFrom(mailMessage.getFrom());
					message.setSentDate(mailMessage.getSentDate());
					message.setText(mailMessage.getText(), mailMessage.isHtml());
				}
			};
			mailSender.send(preparator);
		} else {
			throw new ClassCastException("\"" + message + "\"对象非MailMessage类型！！");
		}
	}


}
