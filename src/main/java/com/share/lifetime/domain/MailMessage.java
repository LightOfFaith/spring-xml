package com.share.lifetime.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MailMessage extends Message {

	private boolean html;

}
