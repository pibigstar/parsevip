package com.pibigstar.parsevip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pibigstar.parsevip.domain.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{

}
