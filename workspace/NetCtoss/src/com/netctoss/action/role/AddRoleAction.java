package com.netctoss.action.role;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.pojo.Role;

public class AddRoleAction {
	
	//输入属性;
	private Role role;
	
	public String execute(){
		//调用DAO将角色信息保存;
		IRoleDao dao = DAOFactory.getIRoleDao();
		try {
			dao.addRole(role);
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