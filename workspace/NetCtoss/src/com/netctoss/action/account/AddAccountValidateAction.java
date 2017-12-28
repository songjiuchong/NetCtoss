package com.netctoss.action.account;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.pojo.Account;

public class AddAccountValidateAction {
	//输入属性;
	private String accountName;
	
	//输出属性;
	private boolean pass;
	
	public String execute(){
		//调用DAO,根据name查询资费数据;
		IAccountDao dao = DAOFactory.getIAccountDao();
		Account account = null;
		try {
			account = dao.findByAccountName(accountName);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//判断查询结果,为空则验证通过,否则不通过;
		if(account == null){
			pass = true;
		}else{
			pass = false;
		}
		//返回success,找对应Result处理输出;
		return "success";
	}
	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
}
