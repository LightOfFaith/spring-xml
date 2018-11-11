package com.share.lifetime.base;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 
 * @author liaoxiang
 * @date 2018/11/11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataSourceHolder {

    private static final ThreadLocal<String> DATASOURCE = new ThreadLocal<String>();

    public static String getDataSource() {
        return DATASOURCE.get();
    }

    public static void setDataSource(String value) {
        DATASOURCE.set(value);
    }

    public static void remove() {
        DATASOURCE.remove();
    }

}
