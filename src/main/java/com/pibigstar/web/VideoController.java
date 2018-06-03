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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 视频
 * @author pibigstar
 *
 */
@RestController
@RequestMapping("video")
@Api(value="视频解析接口类",tags="视频解析")
public class VideoController extends BaseController{
	
	@MyLogger(description = "视频解析")
	@RequestMapping(value = "parse",method=RequestMethod.POST)
	@ApiOperation("视频解析")
	public MyResponse parseUrl(@ApiParam(name = "type",value = "类型1：腾讯，2：人人",required = true)String type,
			@ApiParam(name = "url",value = "视频地址",required = true)String url) {
		log.info("url:"+ url);
		int key = Integer.parseInt(type);
		switch (key) {
		case 1:
			if (url!=null) {
				List<Video> videos = TengXun.parse(url);
				return success(videos);
			}
			break;
		case 2:
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
