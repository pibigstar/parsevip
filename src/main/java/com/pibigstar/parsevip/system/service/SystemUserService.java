package com.pibigstar.parsevip.system.service;

import java.util.List;

import com.pibigstar.parsevip.system.domain.SystemUser;

public interface SystemUserService {
	
	public SystemUser login(SystemUser user);
	
	public List<SystemUser> listUser();
	
	public SystemUser findUserByUsername(String username);
	
	
	public SystemUser update(SystemUser user);
	
	
}
