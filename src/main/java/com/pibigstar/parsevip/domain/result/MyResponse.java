package com.pibigstar.parsevip.domain.result;

/**
 * 页面响应模型
 * @author pibigstar
 *
 */
public class MyResponse {
	
	/**返回状态码**/
	private String resCode = "000";
	/**返回信息*/
	private String resMsg = "操作成功！";
	/**是否成功*/
	private boolean success = false;
	
	private Object data;

	/**
	 * 异常的Response
	 * @param e
	 */
	public MyResponse(ExceptionMsg e) {
		this.resCode = e.getCode();
		this.resMsg = e.getMsg();
		this.success = false;
	}
	public MyResponse(Exception e) {
		this.resCode = "500";
		this.resMsg = e.getMessage();
		this.success = false;
	}
	

	/**
	 * 正常的Response
	 * @param resCode
	 */
	public MyResponse(String resCode) {
		super();
		this.resCode = resCode;
		this.success = true;
	}
	public MyResponse(String resCode,Object data) {
		super();
		this.resCode = resCode;
		this.data = data;
		this.success = true;
	}

	public MyResponse(String resCode, String resMsg) {
		super();
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.success = true;
	}

	public MyResponse(String resCode, String resMsg, Object data) {
		super();
		this.resCode = resCode;
		this.resMsg = resMsg;
		this.data = data;
		this.success = true;
	}
	public String getResCode() {
		return resCode;
	}
	public void setResCode(String resCode) {
		this.resCode = resCode;
	}
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
