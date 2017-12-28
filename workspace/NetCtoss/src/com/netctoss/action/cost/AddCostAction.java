package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.cost.ICostDao;
import com.netctoss.pojo.Cost;

public class AddCostAction {
	//输入属性;
	private Cost cost;
	
	public String execute(){
		//调用DAO,保存输入属性cost;
		ICostDao dao = DAOFactory.getCostDAO();
		try {
			dao.addCost(cost);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,找相应的Result做跳转;
		return "success";
	}
	
	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

}
