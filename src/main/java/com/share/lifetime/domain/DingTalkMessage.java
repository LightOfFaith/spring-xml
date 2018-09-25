package com.share.lifetime.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DingTalkMessage extends Message {

	private String webhook;


}
