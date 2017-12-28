package com.netctoss.vo;

import java.util.List;

import com.netctoss.pojo.Privilege;
import com.netctoss.pojo.Role;

//用于在findRole页面遍历输出每个角色对应的所有权限名;
public class RoleVo extends Role{
	
	private List<Privilege> privileges;

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

}
