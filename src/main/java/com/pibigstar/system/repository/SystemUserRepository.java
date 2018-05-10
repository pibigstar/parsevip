package com.pibigstar.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pibigstar.system.domain.SystemUser;

public interface SystemUserRepository extends JpaRepository<SystemUser, Long>{

	
	//@Query("from system_user u where u.username=:username and u.password=:password")
	//public SystemUser findSystemUser(@Param("username")String username,@Param("password")String password);
	
	public SystemUser findByUsernameAndPassword(String username,String password);
}
