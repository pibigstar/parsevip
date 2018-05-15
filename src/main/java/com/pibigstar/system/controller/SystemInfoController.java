package com.pibigstar.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pibigstar.domain.result.ExceptionMsg;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.system.domain.SystemInfo;
import com.pibigstar.system.repository.SystemInfoRepository;

@RestController
public class SystemInfoController extends SystemBaseController{
	@Autowired
	private SystemInfoRepository systemInfoRepository;
	
	
	@RequestMapping(value="systeminfo/getInfo",method=RequestMethod.GET)
	public ModelAndView getInfo() {
		ModelAndView mv = new ModelAndView();
		List<SystemInfo> infos = systemInfoRepository.findAll();
		
		if (infos!=null&&infos.size()>0) {
			mv.setViewName("admin/system/systemInfo");
			mv.addObject("systemInfo",infos.get(0));
			return mv;
		}else {
			mv.setViewName("admin/system/systemInfo");
			mv.addObject("systemInfo",infos.get(infos.size()-1));
			return mv;
		}
	}
}
