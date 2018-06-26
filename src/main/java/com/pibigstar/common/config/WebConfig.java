package com.pibigstar.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pibigstar.common.Constant;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	/**
	 * 访问外部文件配置，访问D盘下文件
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//配置server虚拟路径，handler为jsp中访问的目录，locations为files相对应的本地路径     
		registry.addResourceHandler("/files/**").addResourceLocations("file:///"+Constant.DEFAULT_FILE_UPLOAD_PATH);  
	}
	
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
