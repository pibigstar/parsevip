package com.pibigstar.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 常量
 * @author pibigstar
 *
 */
public class Constant {

	public static String BASE_PATH;
	public static String LOGIN_USER_SESSION_KEY = "pibigstar";
	public static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";
	public static String default_logo="img/logo.jpg";
	public static int COOKIE_TIMEOUT= 30*24*60*60;
	public static String DEFAULT_ERROR_VIEW = "error/exception";
	
	 @Autowired(required = true)
	  public void setBasePath(@Value("${parsevip.base.path}")String basePath) {
		 Constant.BASE_PATH = basePath;
	  }
	
}
