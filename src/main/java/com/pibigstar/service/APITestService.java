package com.pibigstar.service;

import com.pibigstar.common.annotation.APIMapping;
import com.pibigstar.domain.User;

public class APITestService {
	
	@APIMapping("api.user.getUser")
	public User getUser() {
		User user = new User();
		user.setId(1L);
		user.setUsername("pibigstar");
		return user;
	}

}
