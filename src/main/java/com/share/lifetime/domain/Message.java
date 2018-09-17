package com.share.lifetime.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author liaoxiang
 *
 */
@Getter
@Setter
@ToString
public abstract class Message {

	private String subject;

	private String to;

	private String from;

	private String content;

	private Date sentDate;

}
