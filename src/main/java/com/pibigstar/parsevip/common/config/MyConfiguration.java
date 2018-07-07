package com.pibigstar.parsevip.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取配置文件的自定义常量
 * @author pibigstar
 *
 */
@Configuration
@PropertySource("classpath:myconfig.properties")
@ConfigurationProperties(prefix="parsevip")
public class MyConfiguration {
	
	private String name; //项目名
	private String address; //后台地址
	private String defaultImg; //默认头像
	private String author; //作者名
	
	private String qqGroup; //QQ群
	
	private String addgroupUrl;//加群链接
	
	private String welcome;//欢迎语

	
	

	public String getQqGroup() {
		return qqGroup;
	}

	public void setQqGroup(String qqGroup) {
		this.qqGroup = qqGroup;
	}

	public String getAddgroupUrl() {
		return addgroupUrl;
	}

	public void setAddgroupUrl(String addgroupUrl) {
		this.addgroupUrl = addgroupUrl;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}
