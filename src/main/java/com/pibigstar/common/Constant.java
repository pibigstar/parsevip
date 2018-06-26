package com.pibigstar.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 常量
 * @author pibigstar
 *
 */
@Configuration
public class Constant {

	public static String BASE_PATH;
	public static String LOGIN_USER_SESSION_KEY = "user";
	public static String PASSWORD_KEY = "@#$%^&*()OPG#$%^&*(HG";
	public static String default_logo="img/logo.jpg";
	public static int COOKIE_TIMEOUT= 30*24*60*60;
	public static String DEFAULT_ERROR_VIEW = "error/500";
	
	public static String READ_UPLOAD_FILE = "/files/";
	
	//此数据是读取的配置文件
	public static String DEFAULT_FILE_UPLOAD_PATH;
	//注入
	 @Autowired(required = false)
	 public void setUploadPath(@Value("${parsevip.file.path}")String DEFAULT_FILE_UPLOAD_PATH) {
		 Constant.DEFAULT_FILE_UPLOAD_PATH = DEFAULT_FILE_UPLOAD_PATH;
	 }
	
}
