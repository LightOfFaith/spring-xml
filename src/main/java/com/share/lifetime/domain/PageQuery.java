package com.share.lifetime.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageQuery extends Query {

	private static final long serialVersionUID = 1L;

	private int pageNum;
	private int pageSize;
	private boolean needTotalCount = true;

}
