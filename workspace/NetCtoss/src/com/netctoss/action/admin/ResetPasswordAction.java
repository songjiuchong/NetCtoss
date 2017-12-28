package com.netctoss.action.admin;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.login.IAdminDao;

public class ResetPasswordAction {
	//输入;
	private String ids;
	
	//输出;
	private boolean pass;

	public String execute(){
		//取得需要被重置密码的管理员ID;
		String[] idsArray = ids.split(",");
		//调用DAO将所有选中的管理员密码重置;
		IAdminDao dao = DAOFactory.getAdminDao();
		try {
			dao.resetPassward(idsArray);
			pass=true;
		} catch (DAOException e) {
			e.printStackTrace();
			pass=false;
		}
		return "success";
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

}
