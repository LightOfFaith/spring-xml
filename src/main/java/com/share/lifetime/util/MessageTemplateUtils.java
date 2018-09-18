package com.share.lifetime.util;

public class MessageTemplateUtils {

	public static String getExceptionToTemplate(com.share.lifetime.domain.Exception exception) {
		StringBuilder builder = new StringBuilder();
		builder.append("[项目]：").append("[" + exception.getProject() + "]").append("\n");
		builder.append("[告警对象]：").append("[" + exception.getInstance() + "]").append("\n");
		builder.append("[告警条件]：").append("[" + exception.getCondition() + "]").append("\n");
		builder.append("[异常码值]：").append("[" + exception.getCode() + "]");
		String subCode = exception.getSubCode();
		if (!StringUtils.isEmpty(subCode)) {
			builder.append(",").append("[" + exception.getSubCode() + "]");
		} else {
			builder.append("\n");
		}
		builder.append("[异常描述]：").append("[" + exception.getMsg() + "]");
		String subMsg = exception.getSubMsg();
		if (!StringUtils.isEmpty(subMsg)) {
			builder.append(",").append("[" + exception.getSubMsg() + "]");
		} else {
			builder.append("\n");
		}
		builder.append("[触发时间]：").append("[" + exception.getTimestamp() + "]").append("\n");
		return builder.toString();
	}

}
