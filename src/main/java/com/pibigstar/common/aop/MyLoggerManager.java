package com.pibigstar.common.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pibigstar.utils.IPUtil;

@Aspect //面向切面注解
@Service
public class MyLoggerManager {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HttpServletRequest request;
	
	//只拦截com.pibigstar包下，并且注解为myLogger的方法
	@Before("within(com.pibigstar..*) && @annotation(myLogger)")
	public void printBeforeLog(MyLogger myLogger) {
		String host = IPUtil.getIPAddr(request);
		log.info("========"+host+":开始执行:"+myLogger.description()+"=======");
	}
	//方法结束后
	@After("within(com.pibigstar..*) && @annotation(myLogger)")
	public void printAfterLog(MyLogger myLogger) {
		log.info("========结束执行："+myLogger.description()+"=======");
	}
	//方法抛出异常
	@AfterThrowing(pointcut="within(com.pibigstar..*) && @annotation(myLogger)",throwing = "e")
	public void printExceptionLog(MyLogger myLogger,Exception e) {
		log.info("========执行："+myLogger.description()+"异常："+ e +"=======");
	}

}
