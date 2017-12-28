package com.netctoss.action.admin;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.login.IAdminDao;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.pojo.Admin;
import com.netctoss.pojo.Role;

public class ToUpdateAdminAction {
	
	//输入属性;
	private Integer id;
	
	//输出属性;
	private List<Role> roles;
	
	private Admin admin;

	public String execute(){
		IAdminDao dao = DAOFactory.getAdminDao();
		IRoleDao dao2 = DAOFactory.getIRoleDao();

		try {
			
			//通过ID来获得Admin对象;
			admin = dao.findById(id);
			
			//查询ROLE表中所有的角色信息;
			roles = dao2.findAllRoles();
			
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

}
