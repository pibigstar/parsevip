package com.pibigstar.parsevip.common.exception;

public class ApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String msg;
	
	public ApiException(String msg) {
		this.msg = msg;
	}
	

}
