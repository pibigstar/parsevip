package com.pibigstar.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController{
	
	@RequestMapping("/")
	public String toIndex() {
		setProperties();
		return "kugou";
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
	public String toVideo() {
		setProperties();
		return "lookvideo";
	}
	@RequestMapping("/install")
	public String toInstall() {
		setProperties();
		return "install";
	}

}
