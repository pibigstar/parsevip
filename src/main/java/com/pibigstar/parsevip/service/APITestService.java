package com.pibigstar.parsevip.service;

import com.pibigstar.parsevip.common.annotation.APIMapping;
import com.pibigstar.parsevip.domain.User;

public class APITestService {
	
	@APIMapping("api.user.getUser")
	public User getUser() {
		User user = new User();
		user.setId(1L);
		user.setUsername("pibigstar");
		return user;
	}

}
