package com.pibigstar.system.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 接口
 * @author pibigstar
 *
 */
@Entity
public class SystemInterface {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false,unique=true)
	private String interfaceName;
	@Column(nullable=false)
	private String interfaceUrl;
	@Column(nullable=true)
	private Date lastTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getInterfaceUrl() {
		return interfaceUrl;
	}
	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

}
