package com.share.lifetime.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:config/spring-mvc.xml"})
public class TestControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(characterEncodingFilter, "/*").build();
	}

	@Test
	public void testTest() throws Exception {
		String values = new String(
				"{\"version\":\"09\",\"ins_cd\":\"08M0026034\",\"merchantno_fuiou\":\"0007970F1738384\",\"pay_type\":\"1\",\"scan_type\":\"1\",\"terminal_id\":\"61250648\",\"retri_ref_no\":\"910756564243\",\"channel_trade_no\":\"4200000162201809187171000783\",\"pay_status\":\"1\",\"pay_msg\":\"支付成功\",\"total_fee\":\"2\",\"createtime\":\"20180918110601\",\"settle_date\":\"20180918\",\"terminal_trace\":\"910756564243\",\"buyer_id\":\"\",\"openid\":\"\",\"key_sign\":\"c5c7550fe3478c14989cc272649b66be\"}");
		this.mockMvc
				.perform(get("/test").contentType("application/x-www-form-urlencoded;charset=GBK").param("req", values)
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}
