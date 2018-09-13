package com.share.lifetime.controller;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.share.lifetime.aop.annotation.Log;
import com.share.lifetime.aop.annotation.LogParam;
import com.share.lifetime.exception.BizErrorCode;
import com.share.lifetime.exception.RestErrorCode;
import com.share.lifetime.util.LogType;

@RestController
public class HomeController extends AbstractRestController {

	@Log(logType = LogType.WEB)
	@RequestMapping("/user")
	public String submitForm(@LogParam(value = "newUserForm") NewUserForm newUserForm, Model model,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println(true);
			RestErrorCode errCode = BizErrorCode.MISSING_SIGNATURE_ERROR;
			// throw new BizException("", errCode);
		}
		model.addAttribute("message", "Valid form");
		return "userHome";
	}

}
