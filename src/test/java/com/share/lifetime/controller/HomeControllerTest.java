package com.share.lifetime.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring-mvc.xml"})
public class HomeControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(new CharacterEncodingFilter(), "/*")
				.build();
	}

	@Test
	public void test() throws Exception {
		ResultHandler handler = print();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("email", "06");
		params.add("verifyEmail", "06");
		params.add("password", "12345678");
		params.add("verifyPassword", "12345678");
		this.mockMvc
				.perform(get("/user").params(params).contentType("application/x-www-form-urlencoded;charset=UTF-8")
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(handler).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}
