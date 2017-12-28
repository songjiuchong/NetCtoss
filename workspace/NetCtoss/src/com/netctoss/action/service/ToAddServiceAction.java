package com.netctoss.action.service;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.cost.ICostDao;
import com.netctoss.pojo.Cost;

public class ToAddServiceAction {
	
	//输出属性;
	private List<Cost> costList;

	public String execute(){
		//调用CostDao,查询全部的资费数据;
		ICostDao dao = DAOFactory.getCostDAO();
		try {
			costList = dao.findAll();
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public List<Cost> getCostList() {
		return costList;
	}

	public void setCostList(List<Cost> costList) {
		this.costList = costList;
	}

}
