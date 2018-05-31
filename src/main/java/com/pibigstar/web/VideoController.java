package com.pibigstar.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pibigstar.common.annotation.MyLogger;
import com.pibigstar.domain.result.ExceptionMsg;
import com.pibigstar.domain.result.MyResponse;
import com.pibigstar.parsevip.bean.Video;
import com.pibigstar.parsevip.video.Renren;
import com.pibigstar.parsevip.video.TengXun;

/**
 * 视频
 * @author pibigstar
 *
 */
@RestController
@RequestMapping("video")
public class VideoController extends BaseController{
	
	@MyLogger(description = "视频解析")
	@RequestMapping(value = "parse",method=RequestMethod.POST)
	public MyResponse parseUrl(String type,String url) {
		log.info("url:"+ url);
		int key = Integer.parseInt(type);
		switch (key) {
		case 0:
			if (url!=null) {
				List<Video> videos = TengXun.parse(url);
				return success(videos);
			}
			break;
		case 1:
			if (url!=null) {
				String realUrl = Renren.parse(url);
				return success(realUrl);
			}
		default:
			return error(ExceptionMsg.NOPARSETYPE);
		}
		return error(ExceptionMsg.URLNOTNULL);
	}
	
}
