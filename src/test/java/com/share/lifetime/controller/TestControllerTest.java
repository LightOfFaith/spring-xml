package com.share.lifetime.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:config/spring-mvc.xml" })
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
	public void testISO88591() throws Exception {
		String value = "{\"version\":\"09\",\"ins_cd\":\"08M0026034\",\"merchantno_fuiou\":\"0007970F1738384\",\"pay_type\":\"1\",\"scan_type\":\"1\",\"terminal_id\":\"61250648\",\"retri_ref_no\":\"910756564243\",\"channel_trade_no\":\"4200000162201809187171000783\",\"pay_status\":\"1\",\"pay_msg\":\"支付成功\",\"total_fee\":\"2\",\"createtime\":\"20180918110601\",\"settle_date\":\"20180918\",\"terminal_trace\":\"910756564243\",\"buyer_id\":\"\",\"openid\":\"\",\"key_sign\":\"c5c7550fe3478c14989cc272649b66be\"}";
		this.mockMvc
				.perform(post("/testISO-8859-1").contentType("application/x-www-form-urlencoded").param("req", value)
						.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void testGB2312() throws Exception {
		String value = "{\"version\":\"09\",\"ins_cd\":\"08M0026034\",\"merchantno_fuiou\":\"0007970F1738384\",\"pay_type\":\"1\",\"scan_type\":\"1\",\"terminal_id\":\"61250648\",\"retri_ref_no\":\"910756564243\",\"channel_trade_no\":\"4200000162201809187171000783\",\"pay_status\":\"1\",\"pay_msg\":\"支付成功\",\"total_fee\":\"2\",\"createtime\":\"20180918110601\",\"settle_date\":\"20180918\",\"terminal_trace\":\"910756564243\",\"buyer_id\":\"\",\"openid\":\"\",\"key_sign\":\"c5c7550fe3478c14989cc272649b66be\"}";
		log.info("==========value:{}==========", value);
		this.mockMvc
				.perform(post("/testGB2312").contentType("application/x-www-form-urlencoded;charset=GB2312")
						.param("req", value).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void testGBK() throws Exception {
		String value = "{\"version\":\"09\",\"ins_cd\":\"08M0026034\",\"merchantno_fuiou\":\"0007970F1738384\",\"pay_type\":\"1\",\"scan_type\":\"1\",\"terminal_id\":\"61250648\",\"retri_ref_no\":\"910756564243\",\"channel_trade_no\":\"4200000162201809187171000783\",\"pay_status\":\"1\",\"pay_msg\":\"支付成功\",\"total_fee\":\"2\",\"createtime\":\"20180918110601\",\"settle_date\":\"20180918\",\"terminal_trace\":\"910756564243\",\"buyer_id\":\"\",\"openid\":\"\",\"key_sign\":\"c5c7550fe3478c14989cc272649b66be\"}";
		log.info("==========value:{}==========", value);
		this.mockMvc
				.perform(post("/testGBK").contentType("application/x-www-form-urlencoded;charset=GBK")
						.param("req", value).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void testUnicode() throws Exception {
		String value = "{\"version\":\"09\",\"ins_cd\":\"08M0026034\",\"merchantno_fuiou\":\"0007970F1738384\",\"pay_type\":\"1\",\"scan_type\":\"1\",\"terminal_id\":\"61250648\",\"retri_ref_no\":\"910756564243\",\"channel_trade_no\":\"4200000162201809187171000783\",\"pay_status\":\"1\",\"pay_msg\":\"支付成功\",\"total_fee\":\"2\",\"createtime\":\"20180918110601\",\"settle_date\":\"20180918\",\"terminal_trace\":\"910756564243\",\"buyer_id\":\"\",\"openid\":\"\",\"key_sign\":\"c5c7550fe3478c14989cc272649b66be\"}";
		log.info("==========value:{}==========", value);
		this.mockMvc
				.perform(post("/testUnicode").contentType("application/x-www-form-urlencoded;charset=Unicode")
						.param("req", value).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void testUTF8() throws Exception {
		String value = "{\"version\":\"09\",\"ins_cd\":\"08M0026034\",\"merchantno_fuiou\":\"0007970F1738384\",\"pay_type\":\"1\",\"scan_type\":\"1\",\"terminal_id\":\"61250648\",\"retri_ref_no\":\"910756564243\",\"channel_trade_no\":\"4200000162201809187171000783\",\"pay_status\":\"1\",\"pay_msg\":\"支付成功\",\"total_fee\":\"2\",\"createtime\":\"20180918110601\",\"settle_date\":\"20180918\",\"terminal_trace\":\"910756564243\",\"buyer_id\":\"\",\"openid\":\"\",\"key_sign\":\"c5c7550fe3478c14989cc272649b66be\"}";
		log.info("==========value:{}==========", value);
		this.mockMvc
				.perform(post("/test").contentType("application/x-www-form-urlencoded;charset=UTF-8")
						.param("req", value).accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

}
