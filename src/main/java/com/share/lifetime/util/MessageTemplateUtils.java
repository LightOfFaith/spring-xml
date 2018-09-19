package com.share.lifetime.util;

import java.util.ArrayList;
import java.util.List;

public class MessageTemplateUtils {

	public static List<String> listExceptionToTemplate(com.share.lifetime.domain.Exception exception) {
		List<String> ret = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		ret.add("[项目]：" + exception.getProject());
		ret.add("[告警对象]：" + exception.getInstance());
		ret.add("[告警条件]：" + exception.getCondition());
		StringBuilder code = new StringBuilder();
		code.append(exception.getCode());
		String subCode = exception.getSubCode();
		if (!StringUtils.isEmpty(subCode)) {
			code.append(",").append(subCode);
		}
		ret.add("[异常码值]：" + code);
		StringBuilder msg = new StringBuilder();
		msg.append(exception.getMsg());
		String subMsg = exception.getSubMsg();
		if (!StringUtils.isEmpty(subMsg)) {
			builder.append(",").append(subMsg);
		}
		ret.add("[异常描述]：" + msg);
		ret.add("[触发时间]：" + exception.getTime());

		builder.append("[触发时间]：").append(exception.getTime());
		return ret;
	}


}
