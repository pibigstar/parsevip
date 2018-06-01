package com.pibigstar.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pibigstar.domain.result.ExceptionMsg;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.system.domain.SystemPermission;
import com.pibigstar.system.domain.SystemRole;
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
		//得到当前用户
		Subject subject = SecurityUtils.getSubject();
		//通过前台传递过来的用户名和密码生成token
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		
		try {
			//登录
			subject.login(token);
			
			SystemUser exitUser = (SystemUser) SecurityUtils.getSubject().getPrincipal();
			getSession().setAttribute("user", exitUser);
			return success("登录成功！", exitUser);
			
		} catch (AuthenticationException e) {
			return error(ExceptionMsg.PassWordError);
		}

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
