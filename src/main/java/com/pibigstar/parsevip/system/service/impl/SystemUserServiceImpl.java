package com.pibigstar.parsevip.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.pibigstar.parsevip.system.domain.SystemUser;
import com.pibigstar.parsevip.system.repository.SystemUserRepository;
import com.pibigstar.parsevip.system.service.SystemUserService;

@Service
public class SystemUserServiceImpl implements SystemUserService{
	
	@Autowired
	private SystemUserRepository systemUserRepository;
	
	
	@Override
	public SystemUser login(SystemUser user) {
		SystemUser exitUser = systemUserRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		return exitUser;
	}


	@Override
	public List<SystemUser> listUser() {
		return systemUserRepository.findAll();
	}


	@Override
	public SystemUser findUserByUsername(String username) {
		return systemUserRepository.findByUsername(username);
	}


	@Override
	public SystemUser update(SystemUser user) {
		return systemUserRepository.save(user);
	}

}
