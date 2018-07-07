package com.pibigstar.parsevip.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pibigstar.parsevip.common.Constant;
import com.pibigstar.parsevip.common.utils.IPUtil;
import com.pibigstar.parsevip.domain.result.ExceptionMsg;
import com.pibigstar.parsevip.domain.result.MyResponse;
import com.pibigstar.parsevip.system.domain.SystemUser;
import com.pibigstar.parsevip.system.repository.SystemUserRepository;
import com.pibigstar.parsevip.system.service.SystemUserService;

@RestController
public class SystemUserController extends SystemBaseController{

	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemUserRepository systemUserRepository;
	@Value("${parsevip.openCode}")
	private boolean openCode;

	@RequestMapping("user/login")
	public MyResponse login(SystemUser user,String code,HttpSession httpsession) {
		String imgCode = (String) httpsession.getAttribute("code");
		if (openCode) {
			if (!imgCode.equals(code.toUpperCase())) {
				return error(ExceptionMsg.CodeError);
			}
		}
		//得到当前用户
		Subject subject = SecurityUtils.getSubject();
		//通过前台传递过来的用户名和密码生成token
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),getPwd(user.getPassword()));
		
		try {
			//登录
			subject.login(token);
			
			SystemUser exitUser = (SystemUser) SecurityUtils.getSubject().getPrincipal();
			getSession().setAttribute(Constant.LOGIN_USER_SESSION_KEY, exitUser);
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

	@RequestMapping(value = "add",method=RequestMethod.POST)
	public MyResponse add(SystemUser user) {
		try {
			user.setPassword(getPwd(user.getPassword()));
			user.setCreateTime(new Date());
			user.setState("1");
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
			user.setUpdateTime(new Date());
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
	
	@PostMapping("user/update")
	public MyResponse update(SystemUser user) {
		SystemUser user2 = getUser();
		user.setPassword(user2.getPassword());
		user.setUpdateTime(new Date());
		user.setLastIp(IPUtil.getIPAddr(request));
		user.setHeadImg(user2.getHeadImg());
		getSession().setAttribute(Constant.LOGIN_USER_SESSION_KEY, user);
		return success("更新成功！", systemUserService.update(user));
	}

}
