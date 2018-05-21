package com.pibigstar.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pibigstar.parsevip.bean.Video;
import com.pibigstar.parsevip.video.TengXun;

@Controller
public class APIController extends BaseController{

	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping(value="/vip",method=RequestMethod.GET)
	public ModelAndView play(String url) throws IOException {
		List<Video> videos = null;
		ModelAndView mv = new ModelAndView();
		if (url.contains("v.qq.com")) {
			videos = TengXun.parse(url);
		}
		mv.setViewName("vip");
		mv.addObject("videos",videos.get(0).getUrl());
		return mv;
	}
}
