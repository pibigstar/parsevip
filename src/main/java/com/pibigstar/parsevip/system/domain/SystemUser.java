package com.pibigstar.parsevip.system.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SystemUser {
	
	@GeneratedValue
	@Id
    private Long id;

	@Column(nullable=false,unique=true)
    private String username;

    private String email;
    private String phone;
	
    private String password;
    private Integer age;
	
	private String headImg;
	
    private String signature;
    private String sex;
    private Date createTime;
    private Date updateTime;
    private String state;
    private String emailCode;
    private String lastIp;
    private Date lastTime;
    private String lastAddress;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)//立即从数据库中进行加载数据;
	@JoinTable(name="SystemUserRole",joinColumns= {@JoinColumn(name="userId")},inverseJoinColumns= {@JoinColumn(name="roleId")})
  	private List<SystemRole> roleList;// 一个用户具有多个角色
	
	
	public String getUsername() {
		return username;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEmailCode() {
		return emailCode;
	}
	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getLastAddress() {
		return lastAddress;
	}
	public void setLastAddress(String lastAddress) {
		this.lastAddress = lastAddress;
	}
	public List<SystemRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SystemRole> roleList) {
		this.roleList = roleList;
	}

	

}