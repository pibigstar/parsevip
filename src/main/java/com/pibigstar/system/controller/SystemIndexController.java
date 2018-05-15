package com.pibigstar.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 页面跳转Controller
 * @author pibigstar
 *
 */
@Controller
public class SystemIndexController extends SystemBaseController{
	
	@RequestMapping(value = {"toLogin",""},method=RequestMethod.GET)
	public String toLogin() {
		return adminAdress+"/login/login";
	}
	
	@RequestMapping(value="toIndex",method=RequestMethod.GET)
	public String toIndex() {
		setAttribute();
		return adminAdress+"/index";
	}
	@RequestMapping(value="toMain",method=RequestMethod.GET)
	public String toMain() {
		return adminAdress+"/index/main";
	}
	
	/**
	 * 用户界面
	 * @return
	 */
	@RequestMapping(value="user/toList",method=RequestMethod.GET)
	public String toUserManager() {
		return adminAdress+"/user/listUser";
	}
	@RequestMapping(value="user/toAdd",method=RequestMethod.GET)
	public String toUserAdd() {
		return adminAdress+"/user/addUser";
	}
	
	/**
	 * 接口界面
	 * @return
	 */
	@RequestMapping(value="interface/toList",method=RequestMethod.GET)
	public String toInterface() {
		return adminAdress+"/interface/listinterface";
	}
	@RequestMapping(value="interface/toAdd",method=RequestMethod.GET)
	public String toAddInterface() {
		return adminAdress+"/interface/addInterface";
	}
	
	/**
	 * 公告页面
	 * @return
	 */
	@RequestMapping(value="announce/toAnnounce",method=RequestMethod.GET)
	public String toAnnounce() {
		return adminAdress+"/announce/listAnnounce";
	}
	
	@RequestMapping(value="announce/toAdd",method=RequestMethod.GET)
	public String toAddAnnounce() {
		return adminAdress+"/announce/addAnnounce";
	}
	/**
	 * 友情链接
	 */
	@RequestMapping(value="link/toList",method=RequestMethod.GET)
	public String toLink() {
		return adminAdress+"/link/listLink";
	}
	
	@RequestMapping(value="link/toAdd",method=RequestMethod.GET)
	public String toAddLink() {
		return adminAdress+"/link/addLink";
	}
	
	
	/**
	 * 系统页面
	 */
	@RequestMapping(value="system/404",method=RequestMethod.GET)
	public String to404() {
		return "/error/404";
	}

}
