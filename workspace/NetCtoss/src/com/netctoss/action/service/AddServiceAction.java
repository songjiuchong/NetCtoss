package com.netctoss.action.service;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.pojo.Service;

public class AddServiceAction {
	
	//输入属性;
	private Service service;

	public String execute(){
		//调用DAO,保存输入属性account;
		IServiceDao dao = DAOFactory.getIServiceDao();
		try {
			dao.addService(service);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,找相应的Result做跳转;
		return "success";
	}
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	
}
