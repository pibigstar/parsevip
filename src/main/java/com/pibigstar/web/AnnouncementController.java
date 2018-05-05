package com.pibigstar.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.domain.Announcement;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.service.AnnouncementService;

/**
 * 公告
 * @author pibigstar
 *
 */
@RestController
public class AnnouncementController extends BaseController{
	@Autowired
	private AnnouncementService announcementService;
	
	@RequestMapping(value = "/announcement")
	public MyResponse listAnnoun() {
		List<Announcement> list = announcementService.list();
		return success(list);
	}

}
