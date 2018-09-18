package com.share.lifetime.service;

import com.share.lifetime.domain.Message;

public interface MessageService {

	void sendMessage(Message message) throws Exception;

}
