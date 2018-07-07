package com.pibigstar.parsevip.domain.result;
/**
 * 返回状态码与说明 枚举
 * @author pibigstar
 *
 */
public enum ExceptionMsg {
	SUCCESS("0000","操作成功！"),
	FAILED("9999","操作失败！"),
	PARAMERROR("0001","参数错误！"),
	
    LoginNameOrPassWordError("0010", "用户名或者密码错误！"),
    EmailUsed("0011","该邮箱已被注册"),
    UserNameUsed("0012","该登录名称已存在"),
    EmailNotRegister("0013","该邮箱地址未注册"), 
    LinkOutdated("0014","该链接已过期，请重新请求"),
    PassWordError("0015","密码错误!"),
    UserNameLengthLimit("0016","用户名长度超限"),
    LoginNameNotExists("0017","该用户未注册"),
    UserNameSame("0018","新用户名与原用户名一致"),
    CodeError("0019", "验证码错误！"),
    
    
    ADDERROR("0031", "添加失败！"),
    GETERROR("0032", "查询失败！"),
    DELETEERROR("0033", "删除失败！"),
    UPDATERROR("0034", "更新失败！"),
    
    NULLDATA("0050", "数据为空！"),
	
    NOPARSETYPE("0021","该网站暂时不支持解析"),
    URLNOTNULL("0022","URL不能为空"),
    
    FILLE_IS_NULL("000400","上传文件为空"),
    LimitPictureSize("000401","图片大小必须小于2M"),
    LimitPictureType("000402","图片格式必须为'jpg'、'png'、'jpge'、'gif'、'bmp'");
	
	private String code;
	private String msg;
	private ExceptionMsg(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
