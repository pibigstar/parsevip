package com.pibigstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("music")
	public String toMusic() {
		return "music";
	}

}
