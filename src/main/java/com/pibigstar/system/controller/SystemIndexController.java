package com.pibigstar.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	
	@RequestMapping(value = {"toLogin",""})
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
	@RequestMapping("user/toAdd")
	public String toUserAdd() {
		return adminAdress+"/user/addUser";
	}
	
	/**
	 * 接口界面
	 * @return
	 */
	@RequestMapping("interface/toList")
	public String toInterface() {
		return adminAdress+"/interface/listinterface";
	}
	@RequestMapping("interface/toAdd")
	public String toAddInterface() {
		return adminAdress+"/interface/addInterface";
	}
	
	/**
	 * 公告页面
	 * @return
	 */
	@RequestMapping("announce/toAnnounce")
	public String toAnnounce() {
		return adminAdress+"/announce/listAnnounce";
	}
	
	@RequestMapping("announce/toAdd")
	public String toAddAnnounce() {
		return adminAdress+"/announce/addAnnounce";
	}
	/**
	 * 友情链接
	 */
	@RequestMapping("link/toList")
	public String toLink() {
		return adminAdress+"/link/listLink";
	}
	
	@RequestMapping("link/toAdd")
	public String toAddLink() {
		return adminAdress+"/link/addLink";
	}
	
	
	/**
	 * 系统页面
	 */
	@RequestMapping(value="system/404")
	public String to404() {
		return "/error/404";
	}

}
