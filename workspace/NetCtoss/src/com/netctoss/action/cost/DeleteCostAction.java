package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.cost.ICostDao;

public class DeleteCostAction {

	//输入属性;
	private int id;
	private boolean pass;
	
	public String execute(){
		//调用DAO,根据id删除这条记录;
		ICostDao dao = DAOFactory.getCostDAO();
		try {
			dao.deleteById(id);
			pass=true;
		} catch (DAOException e) {
			e.printStackTrace();
			pass=false;
		}
		//返回success,找到对应的Result做重定向;
		return "success";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

}
