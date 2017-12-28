package com.netctoss.action.admin;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.pojo.Role;

public class ToAddAdminAction {
	//输出;
	private List<Role> roleList;
	
	public String execute(){
		IRoleDao dao = DAOFactory.getIRoleDao();
		try {
			roleList = dao.findAllRoles();
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
}
