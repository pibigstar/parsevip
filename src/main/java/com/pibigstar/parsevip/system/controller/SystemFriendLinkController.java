package com.pibigstar.parsevip.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pibigstar.parsevip.domain.result.ExceptionMsg;
import com.pibigstar.parsevip.domain.result.MyResponse;
import com.pibigstar.parsevip.system.domain.SystemFriendLink;
import com.pibigstar.parsevip.system.repository.SystemFriendLinkRepository;

@RestController
public class SystemFriendLinkController extends SystemBaseController{

	
	@Autowired
	private SystemFriendLinkRepository systemFriendLinkRepository;

	@RequestMapping(value="link/list",method=RequestMethod.GET)
	public MyResponse list() {
		List<SystemFriendLink> links = systemFriendLinkRepository.findAll();
		return success(links);
	}
	
	
	@RequestMapping(value = "link/add",method=RequestMethod.POST)
	public MyResponse add(SystemFriendLink link) {
		try {
			systemFriendLinkRepository.save(link);
			return success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.ADDERROR);
		}
		
	}
	
	
	@RequestMapping(value="link/{id}",method=RequestMethod.DELETE)
	public MyResponse delete(@PathVariable Long id) {
		try {
			systemFriendLinkRepository.deleteById(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.DELETEERROR);
		}
	}
	
	@RequestMapping(value="link/{id}",method=RequestMethod.PUT)
	public MyResponse update(SystemFriendLink link) {
		try {
			systemFriendLinkRepository.save(link);
			return success("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.UPDATERROR);
		}
	}
	
	@RequestMapping(value="link/get/{id}",method=RequestMethod.GET)
	public MyResponse get(@PathVariable Long id) {
		try {
			SystemFriendLink link = systemFriendLinkRepository.getOne(id);
			return success("查询成功！",link);
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.GETERROR);
		}
	}
	@RequestMapping(value="link/{id}",method=RequestMethod.GET)
	public ModelAndView getUserInfo(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		try {
			SystemFriendLink link = systemFriendLinkRepository.getOne(id);
			mv.setViewName(adminAdress+"/link/linkInfo");
			mv.addObject("link",link);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("exception");
			return mv;
		}
	}
	
}
