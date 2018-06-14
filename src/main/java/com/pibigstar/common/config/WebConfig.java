package com.pibigstar.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	/**
	 * 拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//只拦截 /admin/test路径下的所有请求
		registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admin/test/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
