package com.pibigstar.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.parsevip.bean.Video;
import com.pibigstar.parsevip.video.Renren;
import com.pibigstar.parsevip.video.TengXun;
import com.pibigstar.util.JsonResult;

@RestController
@RequestMapping("video")
public class VideoController {
	private Logger log = LoggerFactory.getLogger(VideoController.class);
	@RequestMapping("parse")
	public JsonResult parseUrl(String type,String url) {
		log.info("url:"+ url);
		int key = Integer.parseInt(type);
		switch (key) {
		case 0:
			if (url!=null) {
				List<Video> videos = TengXun.parse(url);
				return JsonResult.success(videos,"OK!");
			}
			break;
		case 1:
			if (url!=null) {
				String realUrl = Renren.parse(url);
				return JsonResult.success(realUrl,"OK!");
			}
		default:
			return JsonResult.error(500, "没有该类型！");
		}
		return JsonResult.error(500, "url为空");
	}
	
}
