package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.cost.ICostDao;
import com.netctoss.pojo.Cost;

public class AddCostValidateAction {
	//输入属性;
	private String name;
	
	//输出属性;
	private boolean pass;
	
	public String execute(){
		//调用DAO,根据name查询资费数据;
		ICostDao dao = DAOFactory.getCostDAO();
		Cost cost = null;
		try {
			cost = dao.findByName(name);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//判断查询结果,为空则验证通过,否则不通过;
		if(cost == null){
			pass = true;
		}else{
			pass = false;
		}
		//返回success,找对应Result处理输出;
		return "success";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

}
