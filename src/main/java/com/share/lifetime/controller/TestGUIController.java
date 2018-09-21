package com.share.lifetime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.share.lifetime.base.AjaxResult;
import com.share.lifetime.exception.BizException;
import com.share.lifetime.exception.DAOException;

@Controller
@RequestMapping("/gui")
public class TestGUIController extends AbstractGUIController {

	@GetMapping("/error500")
	public @ResponseBody AjaxResult<Integer> error500() {
		return success(1 / 0);
	}

	@GetMapping("/errorBiz")
	public @ResponseBody AjaxResult<Integer> errorBiz() {
		throw new BizException("业务处理失败");
	}

	@GetMapping("/errorDao")
	public @ResponseBody AjaxResult<Integer> errorDao() {
		throw new DAOException("语法错误");
	}


}
