package com.share.lifetime.service;

import java.util.Date;

public interface EmailService extends MessageService {

	void sendMessage(String subject, String to, String from, Date sentDate, String content);

	void sendMessage(String subject, String to, String from, Date sentDate, String content, boolean html);

}
