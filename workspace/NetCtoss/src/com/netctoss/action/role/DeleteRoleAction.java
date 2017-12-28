package com.netctoss.action.role;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.role.IRoleDao;

public class DeleteRoleAction {
	
	//输入属性;
	private Integer id;
	
	//输出属性;
	private boolean pass;

	public String execute(){
		//根据id调用删除角色信息的DAO;
		IRoleDao dao = DAOFactory.getIRoleDao();
		try {
			dao.deleteRole(id);
			pass=true;
		} catch (DAOException e) {
			pass=false;
			e.printStackTrace();
		}
		return "success";
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
}
