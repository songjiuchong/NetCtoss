package com.netctoss.action.role;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.pojo.Privilege;
import com.netctoss.pojo.Role;
import com.netctoss.util.PrivilegeReader;

public class ToUpdateRoleAction {
	
	//输入属性;
	private Integer id;
	
	//输出属性;
	private List<Privilege> privileges;
	private Role role;
	
	public String execute(){
		//1,读取XML,获取权限信息;
		privileges = PrivilegeReader.getPrivileges();
		
		//2,调用DAO,根据ID查询角色数据,给role;
		IRoleDao dao = DAOFactory.getIRoleDao();
		try {
			role = dao.findById(id);
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

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
