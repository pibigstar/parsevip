package com.pibigstar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.domain.Announcement;
import com.pibigstar.service.AnnouncementService;
import com.pibigstar.util.JsonResult;

@RestController
public class AnnouncementController {
	@Autowired
	private AnnouncementService announcementService;
	
	@RequestMapping(value = "/announcement")
	public JsonResult listAnnoun() {
		List<Announcement> list = announcementService.list();
		JsonResult jsonResult = JsonResult.success(list);
		return jsonResult;
	}

}
