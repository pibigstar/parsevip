package com.pibigstar.parsevip.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pibigstar.parsevip.common.Constant;
import com.pibigstar.parsevip.common.utils.MD5Util;
import com.pibigstar.parsevip.domain.User;
import com.pibigstar.parsevip.domain.result.ExceptionMsg;
import com.pibigstar.parsevip.domain.result.MyResponse;
import com.pibigstar.parsevip.system.domain.SystemUser;

@Controller
@RequestMapping(value="/admin")
public class SystemBaseController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	protected HttpServletRequest request;
	
	@Value("${parsevip.admin.address}")
	protected String adminAdress;
	@Value("${parsevip.admin.name}")
	protected String adminName;
	@Value("${parsevip.admin.author}")
	protected String adminAuthor;
	@Value("${parsevip.admin.defaultImg}")
	protected String defaultImg;
	
	
	
	
	
	
	/**
	 * 返回成功
	 * @param msg
	 * @return
	 */
	protected MyResponse success() {
		return new MyResponse(ExceptionMsg.SUCCESS.getCode());
	}
	protected MyResponse success(Object data) {
		return new MyResponse(ExceptionMsg.SUCCESS.getCode(),data);
	}
	protected MyResponse success(String msg) {
		return new MyResponse(ExceptionMsg.SUCCESS.getCode(), msg);
	}
	protected MyResponse success(String msg,Object data) {
		return new MyResponse(ExceptionMsg.SUCCESS.getCode(), msg,data);
	}
	/**
	 * 返回异常
	 * @param e
	 * @return
	 */
	protected MyResponse error(ExceptionMsg e) {
		return new MyResponse(e);
	}
	
	
	/**
	 * 得到Seesion对象
	 */
	protected HttpSession getSession() {
		return request.getSession();
	}
	
	/**
	 * 得到session中的用户对象
	 */
	protected SystemUser getUser() {
		return (SystemUser) getSession().getAttribute(Constant.LOGIN_USER_SESSION_KEY);
	}
	
	protected void setAttribute() {
		request.setAttribute("adminAddress", adminAdress);
		request.setAttribute("adminName", adminName);
		request.setAttribute("adminAuthor", adminAuthor);
		request.setAttribute("defaultImg", defaultImg);
	}
	
	protected String getPwd(String pwd) {
		if (pwd!=null&&!"".equals(pwd)) {
			return MD5Util.getMD5(pwd);
		}else {
			return null;
		}
	}

}
