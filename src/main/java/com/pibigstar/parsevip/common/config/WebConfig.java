package com.pibigstar.parsevip.common.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.collect.Maps;
import com.pibigstar.parsevip.common.Constant;
import com.pibigstar.parsevip.common.filter.AdminInterceptor;
import com.pibigstar.parsevip.common.filter.XssFilter;

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
	 * xss过滤拦截器
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = Maps.newHashMap();
		initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
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

	/**
	 * 参数绑定时将yyyy-MM-dd格式的String时间转换为Date
	 */
	@Bean
	public Converter<String, Date> stringDateConvert() {
		return new Converter<String, Date>() {
			@Override
			public Date convert(String source) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = sdf.parse((String) source);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}
}
