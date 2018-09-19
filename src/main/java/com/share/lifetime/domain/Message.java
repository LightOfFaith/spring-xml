package com.share.lifetime.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author liaoxiang
 *
 */
@Getter
@Setter
public abstract class Message {
	
	 public abstract String toJsonString();
}
