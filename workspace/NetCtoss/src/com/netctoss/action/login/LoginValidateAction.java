package com.netctoss.action.login;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.login.IAdminDao;
import com.netctoss.pojo.Admin;

public class LoginValidateAction {
	//输入属性;
	private String name;

	//输出属性;
	private boolean pass;
	
	public String execute(){
		//调用DAO,根据用户名查询是否存在;
		IAdminDao dao = DAOFactory.getAdminDao();
		Admin admin = null;
		try {
			admin = dao.findByUserName(name);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//判断查询结果,为空则验证通过,否则不通过;
		if(admin == null){
			pass = false;
		}else{
			pass = true;
		}
		//返回success,找对应Result处理输出;
		return "success";
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
	
