package com.share.lifetime;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.share.lifetime.entity.Person;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:config/spring-redis.xml")
@ActiveProfiles(profiles = { "test" })
public class RedisTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void test() {
		Person person = new Person();
		person.setName("蚂蚁金服");
		person.setSex(Boolean.TRUE);
		person.setAge(18);
		person.setBirthday(new Date());
		ValueOperations<String, Object> opsForValue = redisTemplate.opsForValue();
		opsForValue.set("person", person);
		Object object = opsForValue.get("person");
		System.out.println(object);
	}

}
