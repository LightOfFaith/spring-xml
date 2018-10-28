package com.share.lifetime.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@PostMapping("/testISO-8859-1")
	public @ResponseBody String testISO88591(HttpServletRequest request) throws IOException, InterruptedException {
		log.info("{}", new String(request.getParameter("req").getBytes("ISO-8859-1"), "UTF-8"));
		return "test";
	}

	@PostMapping("/testGB2312")
	public @ResponseBody String testGB2312(HttpServletRequest request) throws IOException, InterruptedException {
		log.info("{}", new String(request.getParameter("req").getBytes()));
		return "test";
	}

	@PostMapping("/testGBK")
	public @ResponseBody String testGBK(HttpServletRequest request) throws IOException, InterruptedException {
		log.info("{}", new String(request.getParameter("req").getBytes()));
		return "test";
	}

	@PostMapping("/testUnicode")
	public @ResponseBody String testUnicode(HttpServletRequest request) throws IOException, InterruptedException {
		log.info("{}", new String(request.getParameter("req").getBytes()));
		return "test";
	}

	@PostMapping("/testUTF-8")
	public @ResponseBody String testUTF8(HttpServletRequest request) throws IOException, InterruptedException {
		log.info("{}", new String(request.getParameter("req").getBytes()));
		return "test";
	}

	@RequestMapping("/500")
	public @ResponseBody String errorCode500(HttpServletRequest request) {
		return 1 / 0 + "";
	}
	

}
