package com.netctoss.action.account;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;

public class StartAccountAction {
	//输入属性;
	private int id;
	
	//输出属性;
	private boolean pass;
	
	public String execute(){
		//调用DAO,根据id将当前记录的status置为0,删除暂停时间;
		IAccountDao dao = DAOFactory.getIAccountDao();
		try {
			dao.startAccount(id);
			pass = true;
		} catch (DAOException e) {
			e.printStackTrace();
			pass = false;
		}
		//返回success,将输出属性返回给页面;
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}


}

