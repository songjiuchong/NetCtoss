package com.netctoss.action.role;

import java.util.List;

import com.netctoss.pojo.Privilege;
import com.netctoss.util.PrivilegeReader;

public class ToAddRoleAction {
	
	//输出属性:
	private List<Privilege> privileges; //权限信息集合;
	
	public String execute(){
		//解析XML,得到权限信息以在JSP中构建;
		privileges = PrivilegeReader.getPrivileges();
		return "success";
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}
