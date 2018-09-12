package com.share.lifetime.exception;

public interface RestErrorCode extends ErrorCode {

	String getSubCode();

	void setSubCode(String subCode);

	String getSubMsg();

	void setSubMsg(String subMsg);


}
