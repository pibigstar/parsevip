package com.pibigstar.parsevip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pibigstar.parsevip.domain.Announcement;
import com.pibigstar.parsevip.repository.AnnouncementRepository;
import com.pibigstar.parsevip.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{

	@Autowired
	private AnnouncementRepository announcementRepository;
	
	@Override
	@Cacheable("announcements")
	public List<Announcement> list() {
		return announcementRepository.findAll();
	}

}
