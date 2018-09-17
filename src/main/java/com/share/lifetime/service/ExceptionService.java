package com.share.lifetime.service;

import com.share.lifetime.domain.Message;

public interface ExceptionService {

	void handlerException(Message message, com.share.lifetime.domain.Exception exception,
			MessageService messageService);

}
