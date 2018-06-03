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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@Api(value="VIP视频播放",tags="视频播放接口")
public class ParseVIPController extends BaseController{

	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping(value="/vip",method=RequestMethod.GET)
	@ApiOperation("VIP视频播放")
	@ApiImplicitParams(value = { @ApiImplicitParam(name="url",value="视频地址")})
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
