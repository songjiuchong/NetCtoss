package com.netctoss.action.role;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.pojo.Role;

public class UpdateRoleValidateAction {
	
	//输入属性;
	private String roleName;
	private Integer roleId;

	//输出属性;
	private boolean pass;

	public String execute(){
		//根据roleId和roleName调用DAO查找是否存在除roleId所代表的角色名外是否还存在与roleName相同的角色名;
		IRoleDao dao = DAOFactory.getIRoleDao();
		try {
			Role role = dao.validateRoleName(roleId,roleName);
			if(role==null){
				pass=true;
			}else{
				pass=false;
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
	
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
