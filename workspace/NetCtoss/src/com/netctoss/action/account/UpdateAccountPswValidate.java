package com.netctoss.action.account;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.pojo.Account;

public class UpdateAccountPswValidate {
	
	//输入属性;
	private String oldPassword;
	private Integer id;
	
	//输出属性;
	private boolean pass;
	
	public String execute(){
		IAccountDao dao = DAOFactory.getIAccountDao();
		try {
			Account account = dao.findAccountById(id);
			String psw = account.getLoginPassword();
			if(oldPassword!=null&&!oldPassword.equals("")&&oldPassword.equals(psw)){
				pass=true;
			}else{
				pass=false;
			}
		} catch (DAOException e) {
			e.printStackTrace();
			pass=false;
		}
		return "success";
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
