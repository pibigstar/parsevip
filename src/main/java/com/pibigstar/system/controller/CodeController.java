package com.pibigstar.system.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pibigstar.utils.CreateImageCodeUtil;

@Controller
public class CodeController extends SystemBaseController{

	@RequestMapping(value="code/create",method=RequestMethod.GET)
	public void getCode(HttpServletRequest req, HttpServletResponse response,HttpSession session) throws IOException{  
		// 设置响应的类型格式为图片格式  
		response.setContentType("image/jpeg");  
		//禁止图像缓存。  
		response.setHeader("Pragma", "no-cache");  
		response.setHeader("Cache-Control", "no-cache");  
		response.setDateHeader("Expires", 0);  

		//长，宽，验证码个数，干扰线数  
		CreateImageCodeUtil imageCode = new CreateImageCodeUtil(100,30,4,10);  
		session.setAttribute("code", imageCode.getCode().toUpperCase());  
		imageCode.write(response.getOutputStream());  
	}  

	@RequestMapping("code/check")
	public String checkCode(HttpServletRequest request,HttpSession session){  

		String code = request.getParameter("code").toUpperCase();  
		String imgCode =(String)session.getAttribute("code");  

		if (imgCode.equals(code)) {  
			return "login/login";  
		}else{  
			return "error";  
		}  

	}  
}
