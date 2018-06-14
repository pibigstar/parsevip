package com.pibigstar.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pibigstar.common.annotation.MyLogger;
import com.pibigstar.common.utils.IPUtil;

@Aspect //面向切面注解
@Service
public class MyLoggerAspect {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HttpServletRequest request;
	
	//只拦截com.pibigstar包下，并且注解为myLogger的方法
	@Before("within(com.pibigstar..*) && @annotation(myLogger)")
	public void printBeforeLog(MyLogger myLogger) {
		String host = IPUtil.getIPAddr(request);
		log.info("====IP:"+host+":开始执行:"+myLogger.description()+"=======");
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

//	//方法有返回值
//	@AfterReturning(value = "@annotation(myLogger)",returning="result")
//	public void printReturn(Object result) {
//		log.info("======方法的返回值："+result+"========");
//	}
//	
//	//集合前面四大注解
//	@Around("@annotation(myLogger)")
//	public Object printAround(ProceedingJoinPoint joinPoint,MyLogger myLogger) {
//		Object result = null;
//		try {
//			log.info("======开始执行:"+myLogger.description()+"=======");
//			result = joinPoint.proceed(joinPoint.getArgs());
//			log.info("======方法的返回值："+result+"========");
//		} catch (Throwable e) {
//			e.printStackTrace();
//			log.info("======执行异常"+ e +"=======");
//		}finally {
//			log.info("======结束执行："+myLogger.description()+"=======");
//		}
//		return result;
//	}
}
