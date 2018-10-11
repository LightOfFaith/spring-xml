package com.share.lifetime;

import java.math.BigDecimal;

public class Test {

	@org.junit.Test
	public void test() {
		System.out.println(new BigDecimal("0.484").setScale(2, BigDecimal.ROUND_HALF_UP));//四舍五入
		System.out.println(new BigDecimal("0.488").setScale(2, BigDecimal.ROUND_HALF_UP));//四舍五入
		System.out.println(new BigDecimal("0.284").setScale(2, BigDecimal.ROUND_UP));//向上取整
		System.out.println(new BigDecimal("0.284").setScale(2, BigDecimal.ROUND_DOWN));//向下取整
	}

}
