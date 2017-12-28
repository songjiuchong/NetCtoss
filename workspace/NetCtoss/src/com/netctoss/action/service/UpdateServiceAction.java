package com.netctoss.action.service;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.pojo.Service;

public class UpdateServiceAction {
	
	//输入属性;
	private Service service;

	public String execute(){
		//调用DAO,将输入属性cost更新;
		IServiceDao dao = DAOFactory.getIServiceDao();
		try {
			dao.updateService(service);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,找到对应的Result;
		return "success";
	}
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
}
