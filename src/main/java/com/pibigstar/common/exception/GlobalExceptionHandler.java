package com.pibigstar.common.exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pibigstar.common.Constant;
import com.pibigstar.domain.result.MyResponse;

/**
 * 全局异常捕获，统一处理
 * @author pibigstar
 *
 */

@ControllerAdvice // 注解定义全局异常处理类
public class GlobalExceptionHandler {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 处理所有不可知异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	ModelAndView handleException(Exception e, HttpServletRequest request) throws Exception{
		log.info("请求地址：" + request.getRequestURL());
        ModelAndView mav = new ModelAndView();
        //log.error("异常信息：",e);
        log.error("异常信息："+e.getMessage());
        mav.addObject("exception",new MyResponse(e));
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(Constant.DEFAULT_ERROR_VIEW);
        return mav;
	}
	
	
	/**
	 * 处理所有业务逻辑异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	MyResponse handleBusinessException(BusinessException e) {
		log.error(e.getMessage(),e);
		return new MyResponse(e.getErrorCode(),e.getMessage());
	}
	
	
}
