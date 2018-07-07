package com.pibigstar.parsevip.system.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SystemPermission {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;//名称
	
	@Column(columnDefinition="enum('menu','button')")
	private String resourceType;//资源类型 [menu|button]
	
	private String url;//资源路径
	/**
	 * 权限字符串
	 * menu例子：role:*，
	 * button例子：role:create,role:update,role:delete,role:view
	 */
	private String permission;
	
	private Long parentId;//父编号
	private String parentIds;//父编号列表
	private Boolean available = Boolean.FALSE;//是否可用
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="SystemRolePermission",joinColumns= {@JoinColumn(name="permissionId")},inverseJoinColumns= {@JoinColumn(name="roleId")})
	private List<SystemRole> roles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<SystemRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SystemRole> roles) {
		this.roles = roles;
	}
	
	
	
}
