package com.pibigstar.parsevip.system.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SystemRole {

	@Id
	@GeneratedValue
	private Long id;
	
	private String role;//角色标识程序中判断使用,如"admin",这个是唯一的:
	private String description;// 角色描述
	private Boolean available = Boolean.FALSE;//是否可用，如果不可用将不会添加给用户
	
	//角色 -- 权限关系：多对多关系;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="SystemRolePermission",joinColumns= {@JoinColumn(name="roleId")},inverseJoinColumns= {@JoinColumn(name="permissionId")})
	private List<SystemPermission> permissions;
	
	// 用户 - 角色关系定义;
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="SystemUserRole",joinColumns= {@JoinColumn(name="roleId")},inverseJoinColumns= {@JoinColumn(name="userId")})
	private List<SystemUser> userinfos; // 一个角色对应多个用户

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public List<SystemPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SystemPermission> permissions) {
		this.permissions = permissions;
	}

	public List<SystemUser> getUserinfos() {
		return userinfos;
	}

	public void setUserinfos(List<SystemUser> userinfos) {
		this.userinfos = userinfos;
	}
	
	
}
