package com.share.lifetime.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSourceHolder {

	private static final ThreadLocal<String> dataSource = new ThreadLocal<String>();

	public static String getDataSource() {
		return dataSource.get();
	}

	public static void setDataSource(String value) {
		dataSource.set(value);
	}

	public static void remove() {
		dataSource.remove();
	}

}
