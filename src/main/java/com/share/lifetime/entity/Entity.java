package com.share.lifetime.entity;

import java.util.Date;

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
	protected String bizCode;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Entity [id=");
		builder.append(id);
		builder.append(", gmtCreate=");
		builder.append(gmtCreate);
		builder.append(", gmtModified=");
		builder.append(gmtModified);
		builder.append(", creator=");
		builder.append(creator);
		builder.append(", modifier=");
		builder.append(modifier);
		builder.append(", isDeleted=");
		builder.append(isDeleted);
		builder.append(", bizCode=");
		builder.append(bizCode);
		builder.append("]");
		return builder.toString();
	}

}
