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
public class TestController  {

	@RequestMapping("/test")
	public @ResponseBody String test(HttpServletRequest request) throws IOException, InterruptedException {
		// HttpServletRequestBodyWrapper requestBodyWrapper = new HttpServletRequestBodyWrapper(request);
		ServletInputStream inputStream = request.getInputStream();
		log.info("ContentType:{}", request.getContentType());
		log.info("CharacterEncoding:{}", request.getCharacterEncoding());
		log.info("{}", WebUtils.getStreamAsString(inputStream));
		log.info("{}", request.getParameter("name"));
		// log.info("ISO_8859-1:{}", new String(request.getParameter("name").getBytes("ISO_8859-1"), "UTF-8"));
		// Thread.currentThread().sleep(15000);
		return "test";
	}

	@RequestMapping("/500")
	public @ResponseBody String errorCode500(HttpServletRequest request) {
		return 1 / 0 + "";
	}

}
