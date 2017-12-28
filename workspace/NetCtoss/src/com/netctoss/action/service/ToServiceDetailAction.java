package com.netctoss.action.service;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.vo.ServiceVo;

public class ToServiceDetailAction {
	
	//输入属性;
	private Integer serviceId;
	
	//输出属性;
	private ServiceVo serviceVo;
	
	public String execute(){
		IServiceDao dao = DAOFactory.getIServiceDao();
		try {
			serviceVo = dao.findByServiceId(serviceId);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public ServiceVo getServiceVo() {
		return serviceVo;
	}

	public void setServiceVo(ServiceVo serviceVo) {
		this.serviceVo = serviceVo;
	}
}
