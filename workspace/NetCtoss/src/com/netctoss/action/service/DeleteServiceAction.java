package com.netctoss.action.service;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.service.IServiceDao;

public class DeleteServiceAction {
	//输入属性;
	private int serviceId;
	
	//输出属性;
	private boolean pass;
	
	public String execute(){
		//调用DAO,根据id将当前记录的status置为2,记录删除时间;
		IServiceDao dao = DAOFactory.getIServiceDao();
		try {
			dao.deleteService(new int[]{serviceId});
			pass = true;
		} catch (DAOException e) {
			e.printStackTrace();
			pass = false;
		}
		//返回success,将输出属性返回给页面;
		return "success";
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
}
