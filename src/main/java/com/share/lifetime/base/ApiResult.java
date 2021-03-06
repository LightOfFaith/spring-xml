package com.share.lifetime.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiResult<T> extends AbstractResult<T> {

	private static final long serialVersionUID = -3652384809597180181L;

}
