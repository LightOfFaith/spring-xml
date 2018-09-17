package com.share.lifetime.event;

import org.springframework.context.ApplicationEvent;

import com.share.lifetime.domain.Exception;
import com.share.lifetime.domain.Message;
import com.share.lifetime.service.MessageService;

public class ExceptionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1391142300928206477L;

	private Message message;

	private Exception exception;

	private MessageService messageService;

	public ExceptionEvent(Object source, Message message, Exception exception, MessageService messageService) {
		super(source);
		this.message = message;
		this.exception = exception;
		this.messageService = messageService;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}


}
