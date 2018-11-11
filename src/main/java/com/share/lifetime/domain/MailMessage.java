package com.share.lifetime.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailMessage extends AbstractMessage {

	private String subject;

	private String to;

	private String from;

	private String text;

	private boolean html;
	
	private Date sentDate;

	@Override
	public String toJsonString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
	}

	
}
