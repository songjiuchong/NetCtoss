package com.netctoss.action.role;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.pojo.Role;

public class UpdateRoleAction {
	
	//输入属性;
	private Role role;

	public String execute(){
		//调用DAO,更新角色信息;
		IRoleDao dao = DAOFactory.getIRoleDao();
		try {
			dao.updateRole(role);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
