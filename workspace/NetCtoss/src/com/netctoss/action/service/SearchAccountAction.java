package com.netctoss.action.service;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.pojo.Account;

public class SearchAccountAction {
	
	//输入属性;
	private String idcardNo;
	
	//输出属性;
	private Account account;
	
	public String execute(){
		//调用DAO,根据身份证查询账务账号数据;
		IAccountDao dao = DAOFactory.getIAccountDao();
		try {
			account = dao.findAccountByIdCardNo(idcardNo);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
