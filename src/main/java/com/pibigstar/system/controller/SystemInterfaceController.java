package com.pibigstar.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.domain.result.ExceptionMsg;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.system.domain.SystemInterface;
import com.pibigstar.system.repository.SystemInterfaceRepository;

@RestController
public class SystemInterfaceController extends SystemBaseController{
	
	@Autowired
	private SystemInterfaceRepository systemInterfaceRepository;
	
	@RequestMapping(value="interface/list",method=RequestMethod.GET)
	public MyResponse list() {
		List<SystemInterface> interfaces = systemInterfaceRepository.findAll();
		return success(interfaces);
	}
	
	
	@RequestMapping(value = "interface/add",method=RequestMethod.POST)
	public MyResponse add(SystemInterface inter) {
		try {
			systemInterfaceRepository.save(inter);
			return success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.ADDERROR);
		}
		
	}
	
	
	@RequestMapping(value="interface/{id}",method=RequestMethod.DELETE)
	public MyResponse delete(@PathVariable Long id) {
		try {
			systemInterfaceRepository.deleteById(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.DELETEERROR);
		}
	}
	
	@RequestMapping(value="interface/{id}",method=RequestMethod.GET)
	public MyResponse get(@PathVariable Long id) {
		try {
			SystemInterface inter = systemInterfaceRepository.getOne(id);
			return success("查询成功！",inter);
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.GETERROR);
		}
	}
	

}
