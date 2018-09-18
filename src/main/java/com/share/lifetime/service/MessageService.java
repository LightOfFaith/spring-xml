package com.share.lifetime.service;

import java.io.IOException;

import com.share.lifetime.domain.Message;

public interface MessageService {

	void sendMessage(Message message) throws IOException;

}
