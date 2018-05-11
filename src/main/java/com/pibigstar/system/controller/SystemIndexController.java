package com.pibigstar.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pibigstar.domain.result.ExceptionMsg;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.system.domain.SystemUser;
import com.pibigstar.system.repository.SystemUserRepository;

/**
 * 页面跳转Controller
 * @author pibigstar
 *
 */
@Controller
public class SystemIndexController extends SystemBaseController{
	
	@Autowired
	private SystemUserRepository systemUserRepository;
	
	
	@RequestMapping(value = {"toLogin","/"})
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
	
	/**
	 * 用户界面
	 * @return
	 */
	@RequestMapping("user/toList")
	public String toUserManager() {
		return adminAdress+"/user/listUser";
	}
	@RequestMapping("user/toAddUser")
	public String toUserAdd() {
		return adminAdress+"/user/addUser";
	}
	
	@RequestMapping(value = "user/{id}",method=RequestMethod.GET)
	public String get(@PathVariable Long id) {
		try {
			SystemUser user = systemUserRepository.getOne(id);
			request.setAttribute("userInfo", user);
			return adminAdress+"/user/userInfo";
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
		
	}
	
	
	
	/**
	 * 接口界面
	 * @return
	 */
	@RequestMapping("interface/toList")
	public String toInterface() {
		return adminAdress+"/interface/listinterface";
	}
	@RequestMapping("interface/toAddInterface")
	public String toAddInterface() {
		return adminAdress+"/interface/addInterface";
	}

}
