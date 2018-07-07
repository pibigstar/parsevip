package com.pibigstar.parsevip.common.exception;

/**
 * 自定义异常
 * @author pibigstar
 *
 */
public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = -7783716308438092905L;
	
	private String errorCode;//异常代码
	private String message;//异常信息
	
	public BusinessException() {
		super();
	}

	public BusinessException(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
