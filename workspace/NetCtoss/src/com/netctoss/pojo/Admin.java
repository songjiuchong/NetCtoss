package com.netctoss.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Admin {
	private Integer id; //ID
	private String adminCode; //ADMIN_CODE;
	private String password; //PASSWORD;
	private String name; //NAME;
	private String telephone; //TELEPHONE;
	private String email; //EMAIL;
	private Date enrollDate; //ENROLLDATE;
	
	private String[] roleIds;

	//设置一个连接目标ADMIN对象拥有的所有角色名称的字符串;
	private String roleNames;
	
	//追加属性,用于存储相关的Role对象信息;
	private Set<Role> roles = new HashSet<Role>();
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(String adminCode) {
		this.adminCode = adminCode;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	
}
