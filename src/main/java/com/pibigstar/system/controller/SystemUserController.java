package com.pibigstar.system.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pibigstar.domain.result.ExceptionMsg;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.system.domain.SystemUser;
import com.pibigstar.system.repository.SystemUserRepository;
import com.pibigstar.system.service.SystemUserService;

@RestController
public class SystemUserController extends SystemBaseController{
	
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemUserRepository systemUserRepository;
	
	
	@RequestMapping("user/login")
	public MyResponse login(SystemUser user,String code,HttpSession httpsession) {
		String imgCode = (String) httpsession.getAttribute("code");
		if (!imgCode.equals(code.toUpperCase())) {
			return error(ExceptionMsg.CodeError);
		}
		
		SystemUser exitUser = systemUserService.login(user);
		if(exitUser==null) {
			return error(ExceptionMsg.LoginNameOrPassWordError);
		}
		return success("登录成功！", exitUser);
	}
	
	
	@RequestMapping("user/list")
	public MyResponse list() {
		List<SystemUser> users = systemUserService.listUser();
		return success(users);
	}
	
	@RequestMapping(value = "user/add",method=RequestMethod.POST)
	public MyResponse add(SystemUser user) {
		try {
			systemUserRepository.save(user);
			return success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.ADDERROR);
		}
		
	}
	@RequestMapping(value = "user/{id}",method=RequestMethod.DELETE)
	public MyResponse delete(@PathVariable Long id) {
		try {
			systemUserRepository.deleteById(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.DELETEERROR);
		}
		
	}
	@RequestMapping(value = "user/{id}",method=RequestMethod.PUT)
	public MyResponse update(@PathVariable Long id,@ModelAttribute SystemUser user) {
		try {
			systemUserRepository.saveAndFlush(user);
			return success("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.UPDATERROR);
		}
	}
	
	@RequestMapping(value = "user/{id}",method=RequestMethod.GET)
	public ModelAndView getUserInfo(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName(adminAdress+"/user/userInfo");
			SystemUser user = systemUserRepository.getOne(id);
			mv.addObject("userInfo", user);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("exception");
			return mv;
		}
	}

}
