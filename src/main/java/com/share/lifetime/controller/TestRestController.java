package com.share.lifetime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.share.lifetime.base.RestApiResult;
import com.share.lifetime.exception.BizException;
import com.share.lifetime.exception.DAOException;

@Controller
@RequestMapping("/rest")
public class TestRestController extends AbstractRestController {

	@GetMapping("/error500")
	public @ResponseBody RestApiResult<Integer> error500() {
		return success(1 / 0);
	}

	@GetMapping("/errorBiz")
	public @ResponseBody RestApiResult<Integer> errorBiz() {
		throw new BizException("业务处理失败");
	}

	@GetMapping("/errorDao")
	public @ResponseBody RestApiResult<Integer> errorDao() {
		throw new DAOException("语法错误");
	}

}
