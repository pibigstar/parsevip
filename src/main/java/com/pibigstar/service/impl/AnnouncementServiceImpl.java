package com.pibigstar.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pibigstar.domain.Announcement;
import com.pibigstar.repository.AnnouncementRepository;
import com.pibigstar.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{

	@Autowired
	private AnnouncementRepository announcementRepository;
	
	@Override
	public List<Announcement> list() {
		return announcementRepository.findAll();
	}

}
