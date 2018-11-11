package com.share.lifetime.service;

import com.share.lifetime.domain.AbstractMessage;

public interface ExceptionService {

	void handlerException(AbstractMessage message, com.share.lifetime.domain.Exception exception,
			MessageService messageService);

}
