package com.share.lifetime.controller;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.share.lifetime.util.WebUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController extends AbstractRestController {

	@RequestMapping("/test")
	public @ResponseBody String test(HttpServletRequest request) throws IOException, InterruptedException {
		// HttpServletRequestBodyWrapper requestBodyWrapper = new HttpServletRequestBodyWrapper(request);
		ServletInputStream inputStream = request.getInputStream();
		log.info("{}", WebUtils.getStreamAsString(inputStream));
		log.info("{}", request.getParameter("name"));
		Thread.currentThread().sleep(15000);
		return "test";
	}

}
