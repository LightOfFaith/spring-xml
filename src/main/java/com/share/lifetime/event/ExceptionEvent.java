package com.share.lifetime.event;

import org.springframework.context.ApplicationEvent;

import com.share.lifetime.domain.Exception;

public class ExceptionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1391142300928206477L;
	
	private Exception exception;
	
	public ExceptionEvent(Object source) {
		super(source);
	}

	public ExceptionEvent(Object source, Exception exception) {
		super(source);
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	
	
	

}
