package com.pibigstar.parsevip.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 管理员拦截器
 * 如果不是管理员则不让其访问
 * @author pibigstar
 *
 */
public class AdminInterceptor implements HandlerInterceptor{
	
	/**
	 * 在请求处理之前进行调用（controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (request.getAttribute("userId").equals("admin")) {
			//放行
			return true;
		}
		//拦截
		return false;
	}
	
	/**
	 * 在整个请求结束之后被调用
	 * 主要用来进行资源清理 ，一些缓存什么的。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
