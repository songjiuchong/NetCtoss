package com.netctoss.action.admin;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.login.IAdminDao;
import com.netctoss.pojo.Admin;

public class UpdateAdminAction {
	
	//输入属性;
	private Admin admin;

	public String execute(){
		//根据ADMIN对象来更新其相关内容;
		IAdminDao dao = DAOFactory.getAdminDao();
		try {
			dao.updateAdmin(admin);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
