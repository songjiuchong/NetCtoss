package com.netctoss.action.account;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.pojo.Account;

public class AddAccountAction {
	//输入属性;
	private Account account;
	
	public String execute(){
		//调用DAO,保存输入属性account;
		IAccountDao dao = DAOFactory.getIAccountDao();
		try {
			dao.addAccount(account);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,找相应的Result做跳转;
		return "success";
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
