package com.netctoss.action.service;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.pojo.Account;

public class StartServiceAction {
	
	//输入属性;
	private Integer serviceId;
	private Integer accountId;
	
	//输出属性;
	private String message; //提示信息;
	
	public String execute(){
		
		//1,判断对应的账务账号是否暂停/删除;
		IAccountDao dao = DAOFactory.getIAccountDao();
		
		Account a = null;
		try {
			a = dao.findAccountById(accountId);
		}catch (DAOException e1) {
			e1.printStackTrace();
			message = "开通失败！";
			return "success";
		}
		if(a.getStatus().equals("1")||a.getStatus().equals("2")){
			message = "对应的账务账号已暂停或删除,不能进行此项操作";
			return "success";
		} 
		
		//2,调用DAO,根据id将当前记录的status置为0,删除暂停时间;
		IServiceDao dao2 = DAOFactory.getIServiceDao();
		
		try {
			dao2.startService(serviceId);
			message = "开通成功！";
		} catch (DAOException e) {
			e.printStackTrace();
			message = "开通失败！";
		}
		//返回success,将输出属性返回给页面;
		return "success";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

}
