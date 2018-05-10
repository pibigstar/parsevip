package com.pibigstar.system.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping("user/add")
	public MyResponse add(SystemUser user) {
		try {
			systemUserRepository.save(user);
			return success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.ADDUSERERROR);
		}
		
	}

}
