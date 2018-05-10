package com.pibigstar.system.service;

import java.util.List;

import com.pibigstar.system.domain.SystemUser;

public interface SystemUserService {
	
	public SystemUser login(SystemUser user);
	
	public List<SystemUser> listUser();
	
}
