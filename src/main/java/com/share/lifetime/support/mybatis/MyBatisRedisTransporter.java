package com.share.lifetime.support.mybatis;

import org.springframework.data.redis.core.RedisTemplate;

public class MyBatisRedisTransporter {

	public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
		MyBatisRedisCache.setRedisTemplate(redisTemplate);
	}


}
