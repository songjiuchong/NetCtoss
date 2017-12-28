package com.netctoss.action.admin;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.login.IAdminDao;

public class DeleteAdminAction {
	
	//输入属性;
	private Integer id;
	
	//输出属性;
	private boolean pass;

	public String execute(){
		//根据ID来删除相应的管理员记录;
		IAdminDao dao = DAOFactory.getAdminDao();
		try {
			dao.deleteById(id);
			pass=true;
		} catch (DAOException e) {
			e.printStackTrace();
			pass=false;
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
