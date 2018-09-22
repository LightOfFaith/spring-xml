package com.share.lifetime.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

	@GetMapping("/testGUI")
	public ModelAndView testGUI() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("gui", "hello gui");
		return success("testgui", model);
	}

	@GetMapping("/testGUI1")
	public String testGUI1() {
		return "testgui";
	}

}
