package com.pibigstar.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.pibigstar.domain.Announcement;
import com.pibigstar.domain.result.ExceptionMsg;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.repository.AnnouncementRepository;

/**
 * 公告
 * @author pibigstar
 *
 */
@RestController
public class SystemAnnounController extends SystemBaseController{
	
	@Autowired
	private AnnouncementRepository announcementRepository;

	@RequestMapping(value="announce/list",method=RequestMethod.GET)
	public MyResponse list() {
		List<Announcement> announs = announcementRepository.findAll();
		return success(announs);
	}
	
	
	@RequestMapping(value = "announce/add",method=RequestMethod.POST)
	public MyResponse add(Announcement announ) {
		try {
			announcementRepository.save(announ);
			return success("添加成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.ADDERROR);
		}
		
	}
	
	
	@RequestMapping(value="announce/{id}",method=RequestMethod.DELETE)
	public MyResponse delete(@PathVariable Long id) {
		try {
			announcementRepository.deleteById(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.DELETEERROR);
		}
	}
	
	@RequestMapping(value="announce/{id}",method=RequestMethod.PUT)
	public MyResponse update(Announcement announ) {
		try {
			announcementRepository.save(announ);
			return success("更新成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.UPDATERROR);
		}
	}
	
	@RequestMapping(value="announce/get/{id}",method=RequestMethod.GET)
	public MyResponse get(@PathVariable Long id) {
		try {
			Announcement announ = announcementRepository.getOne(id);
			return success("查询成功！",announ);
		} catch (Exception e) {
			e.printStackTrace();
			return error(ExceptionMsg.GETERROR);
		}
	}
	@RequestMapping(value="announce/{id}",method=RequestMethod.GET)
	public ModelAndView getUserInfo(@PathVariable Long id) {
		ModelAndView mv = new ModelAndView();
		try {
			Announcement announ = announcementRepository.getOne(id);
			mv.setViewName(adminAdress+"/announce/announceInfo");
			mv.addObject("announce",announ);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("exception");
			return mv;
		}
	}
	
}
