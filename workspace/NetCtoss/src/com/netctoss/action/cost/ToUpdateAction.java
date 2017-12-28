package com.netctoss.action.cost;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.cost.ICostDao;
import com.netctoss.pojo.Cost;

public class ToUpdateAction {
	
	//输入属性;
	private Integer id;
	//输出属性;
	private Cost cost;
	
	public String execute(){
		//根据输入属性id,查询这条资费数据,赋值给输出属性;
		ICostDao dao = DAOFactory.getCostDAO();
		try {
			cost = dao.findById(id);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//如果登陆成功过的用户直接访问../toUpdateCost.action则会出现空id,所以需要限制;
		if(cost==null)
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
	public Cost getCost() {
		return cost;
	}
	public void setCost(Cost cost) {
		this.cost = cost;
	}
	
}
