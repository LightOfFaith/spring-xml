package com.share.lifetime.domain;

import java.util.LinkedList;
import java.util.List;

import com.share.lifetime.util.JsonUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DingTalkMarkdownMessage extends AbstractDingTalkMessage {
	
	private String title;
	
	private List<String> items = new LinkedList<String>();

	@Override
	public String toJsonString() {
		return JsonUtils.toJson(this);
	}

}
