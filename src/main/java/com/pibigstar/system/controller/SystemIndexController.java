package com.pibigstar.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 * @author pibigstar
 *
 */
@Controller
public class SystemIndexController extends SystemBaseController{
	
	
	@RequestMapping("toLogin")
	public String toLogin() {
		return adminAdress+"/login/login";
	}
	
	@RequestMapping("toIndex")
	public String toIndex() {
		setAttribute();
		return adminAdress+"/index";
	}
	@RequestMapping("toMain")
	public String toMain() {
		return adminAdress+"/index/main";
	}
	
	@RequestMapping("user/toList")
	public String toUserManager() {
		return adminAdress+"/user/listUser";
	}
	@RequestMapping("user/toAddUser")
	public String toUserAdd() {
		return adminAdress+"/user/addUser";
	}

}
