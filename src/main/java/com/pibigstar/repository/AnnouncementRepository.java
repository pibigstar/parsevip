package com.pibigstar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pibigstar.domain.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long>{

}
