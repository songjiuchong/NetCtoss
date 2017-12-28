package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.cost.ICostDao;
import com.netctoss.pojo.Cost;

public class UpdateCostAction {
	
	//输入属性;
	private Cost cost;
	
	public String execute(){
		//调用DAO,将输入属性cost更新;
		ICostDao dao = DAOFactory.getCostDAO();
		try {
			dao.updateCost(cost);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,找到对应的Result;
		return "success";
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}
	

	
}
