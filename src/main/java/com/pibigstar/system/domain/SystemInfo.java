package com.pibigstar.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SystemInfo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String systemEdition; //版本
	@Column(nullable=true)
	private String systemAuthor;	//作者
	@Column(nullable=true)
	private String systemIndex; //网站首页
	@Column(nullable=true)
	private String systemEnvironment; //服务器环境
	@Column(nullable=true)
	private String systemDataBaseEdition; //数据库版本
	@Column(nullable=true)
	private String systemUploadSize; //上传最大限制
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSystemEdition() {
		return systemEdition;
	}
	public void setSystemEdition(String systemEdition) {
		this.systemEdition = systemEdition;
	}
	public String getSystemAuthor() {
		return systemAuthor;
	}
	public void setSystemAuthor(String systemAuthor) {
		this.systemAuthor = systemAuthor;
	}
	public String getSystemIndex() {
		return systemIndex;
	}
	public void setSystemIndex(String systemIndex) {
		this.systemIndex = systemIndex;
	}
	public String getSystemEnvironment() {
		return systemEnvironment;
	}
	public void setSystemEnvironment(String systemEnvironment) {
		this.systemEnvironment = systemEnvironment;
	}
	public String getSystemDataBaseEdition() {
		return systemDataBaseEdition;
	}
	public void setSystemDataBaseEdition(String systemDataBaseEdition) {
		this.systemDataBaseEdition = systemDataBaseEdition;
	}
	public String getSystemUploadSize() {
		return systemUploadSize;
	}
	public void setSystemUploadSize(String systemUploadSize) {
		this.systemUploadSize = systemUploadSize;
	}
	
}
