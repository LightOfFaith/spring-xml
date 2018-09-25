package com.share.lifetime.entity;

import java.util.Date;

import com.share.lifetime.util.JsonUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Entity {

	protected String id;
	protected Date gmtCreate;
	protected Date gmtModified;
	protected String creator;
	protected String modifier;
	protected String isDeleted;
	protected String code;

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

}
