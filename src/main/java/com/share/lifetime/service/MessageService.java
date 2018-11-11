package com.share.lifetime.service;

import com.share.lifetime.domain.AbstractMessage;

public interface MessageService {

	void sendMessage(AbstractMessage message) throws Exception;

}
