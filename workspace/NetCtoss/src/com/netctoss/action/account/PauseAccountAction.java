package com.netctoss.action.account;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.pojo.Service;

public class PauseAccountAction {
	//输入属性;
	private int id;
	
	//输出属性;
	private boolean pass;
	
	public String execute(){
		//1,调用DAO,根据id将当前记录的status置为1,设置暂停时间为当前系统时间;
		IAccountDao dao = DAOFactory.getIAccountDao();
		try {
			dao.pauseAccount(id);
			pass = true;
		} catch (DAOException e) {
			e.printStackTrace();
			pass = false;
		}
		//2,调用IServiceDao,根据账务账号ID查询相关业务账号;
		IServiceDao dao2 = DAOFactory.getIServiceDao();
		List<Service> services = null;
		try {
			services = dao2.findServiceByAccountId(id);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//3,调用IServiceDao将返回的一组业务账号数据暂停;
		try {
			int[] id = getIdFromServices(services);
			dao2.pauseService(id);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,将输出属性返回给页面;
		return "success";
	}
	
	//根据账务账号数据集合提取出所有ID;
	private int[] getIdFromServices(List<Service> services){
		if(services == null||services.size() == 0){
			return null;
		}
		int[] id = new int[services.size()];
		for(int i=0;i<services.size();i++){
			Service s = services.get(i);
			id[i] = s.getServiceId();
		}
		return id;
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
