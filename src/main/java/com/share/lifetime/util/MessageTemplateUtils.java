package com.share.lifetime.util;

import java.util.ArrayList;
import java.util.List;

public class MessageTemplateUtils {

	public static List<String> getExceptionToTemplate(com.share.lifetime.domain.Exception exception) {
		List<String> ret = new ArrayList<>();
		ret.add("[项        目]:" + exception.getProject());
		ret.add("[告警对象]:" + exception.getInstance());
		ret.add("[告警条件]:" + exception.getCondition());
		StringBuilder code = new StringBuilder();
		code.append(exception.getCode());
		String subCode = exception.getSubCode();
		if (!StringUtils.isEmpty(subCode)) {
			code.append(";").append(subCode);
		}
		ret.add("[异常码值]:" + code);
		StringBuilder msg = new StringBuilder();
		msg.append(exception.getMsg());
		String subMsg = exception.getSubMsg();
		if (!StringUtils.isEmpty(subMsg)) {
			msg.append(";").append(subMsg);
		}
		ret.add("[异常描述]:" + msg);
		ret.add("[触发时间]:" + exception.getTime());
		ret.add("[备        注]:" + exception.getRemark());
		return ret;
	}


}
