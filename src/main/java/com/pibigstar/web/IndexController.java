package com.pibigstar.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pibigstar.system.domain.SystemInterface;
import com.pibigstar.system.repository.SystemInterfaceRepository;

@Controller
public class IndexController extends BaseController{
	@Autowired
	private SystemInterfaceRepository systemInterfaceRepository;
	
	@RequestMapping("/")
	public String toDefaultIndex() {
		setProperties();
		return "kugou";
	}
	//登录成功后的回调地址
	@RequestMapping("/index")
	public String toIndex() {
		setProperties();
		return "index";
	}
	@RequestMapping("/music/kugou")
	public String toKuGouMusic() {
		setProperties();
		return "kugou";
	}
	@RequestMapping("/music/QQ")
	public String toQQMusic() {
		setProperties();
		return "QQMusic";
	}
	@RequestMapping("/video/tengxun")
	public String toTengXunVideo() {
		setProperties();
		return "tengxun";
	}
	@RequestMapping("/video/renren")
	public String toRenRenVideo() {
		setProperties();
		return "renren";
	}
	@RequestMapping("/message")
	public String toMessage() {
		setProperties();
		return "message";
	}
	@RequestMapping("/exception")
	public String toError() {
		return "exception";
	}
	
	@RequestMapping("/look")
	public ModelAndView toVideo() {
		setProperties();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("lookvideo");
		List<SystemInterface> interfaces = systemInterfaceRepository.findAll();
		mv.addObject("interfaces",interfaces);
		return mv;
	}
	@RequestMapping("/install")
	public String toInstall() {
		setProperties();
		return "install";
	}

}
