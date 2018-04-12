package com.pibigstar.util;
/**
 * 封装的JSON对象
 * @author pibigstar
 *
 */
public class JsonResult {
	
	private boolean success = false;
	private int code;
	private String msg;
	private Object data;
	
	public static JsonResult error(int code,String msg) {
		JsonResult result = new JsonResult();
		result.setSuccess(false);
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	public static JsonResult success(Object data,String msg) {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		result.setCode(200);
		result.setMsg(msg);
		result.setData(data);
		return result;
		
	}
	public static JsonResult success(Object data) {
		JsonResult result = new JsonResult();
		result.setSuccess(true);
		result.setCode(200);
		result.setMsg("OK!");
		result.setData(data);
		return result;
		
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
