package com.netctoss.action.service;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.cost.ICostDao;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.pojo.Cost;
import com.netctoss.pojo.Service;

public class ToUpdateServiceAction {
	//输入属性;
	private Integer id;
	//输出属性;
	private Service service;
	private List<Cost> costList;
	
	public String execute(){
		//调用CostDao,查询全部的资费数据;
		ICostDao dao = DAOFactory.getCostDAO();
		//根据输入属性id,查询这条资费数据,赋值给输出属性;
		IServiceDao dao2 = DAOFactory.getIServiceDao();
		try {
			costList = dao.findAll();
			service = dao2.findById(id);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//如果登陆成功过的用户直接访问../toUpdateService.action则会出现空id,所以需要限制;
		if(service==null)
			return "login";
		//返回success,找到转发的Result;
		return "success";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
	public List<Cost> getCostList() {
		return costList;
	}

	public void setCostList(List<Cost> costList) {
		this.costList = costList;
	}
}
