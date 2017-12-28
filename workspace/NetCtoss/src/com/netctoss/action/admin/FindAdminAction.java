package com.netctoss.action.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.netctoss.action.BaseAction;
import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.login.IAdminDao;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.pojo.Admin;
import com.netctoss.pojo.Privilege;
import com.netctoss.pojo.Role;
import com.netctoss.util.PrivilegeReader;

public class FindAdminAction extends BaseAction{
	
	//输入属性;
	
	private String privilegeId;
	
	private String roleId;
	
	private int page=1; //当前页;
	
	private int pageSize; //页容量;
	
	//输出属性;
	
	private List<Admin> admins;
	
	private int totalPages;
	
	private List<Role> roles; //用于构建findAdmin页面中角色的下拉列表;
	
	private List<Privilege> privileges; //用于构建findAdmin页面中权限的下拉列表;
	
	public String execute(){
		
		IAdminDao dao = DAOFactory.getAdminDao();
		IRoleDao dao2 = DAOFactory.getIRoleDao();
		try {
			//读取XML,获取权限信息;
			List<Privilege> privilegesT = PrivilegeReader.getPrivileges(); //这里要注意不要误用引用传递,因为PrivilegeReader类中的privileges静态变量是公共的,不应该被改变;
			List<Privilege> privilegesTT = new ArrayList<Privilege>();
			for(Privilege p:privilegesT){
				privilegesTT.add(p);
			}
			
			//为了满足下拉选项中的"全部"这个选项的功能,这里需要临时增加这一项;
			//注意:还有一种方法是在前端页面中的struts标签select中使用headerKey/headerValue,也可以指定默认的最初下拉框选项;
			Privilege allPrivilege = new Privilege();
			allPrivilege.setId("-1");
			allPrivilege.setName("全部");
			privilegesTT.add(allPrivilege);
			//为LIST中对象按照ID排序;
			Collections.sort(privilegesTT,new Comparator<Privilege>(){
				public int compare(Privilege arg0, Privilege arg1) {
					Integer p1 = Integer.parseInt(arg0.getId());
					Integer p2 = Integer.parseInt(arg1.getId());
					return p1-p2;
				}
			});
			privileges = privilegesTT;
			
			//查询ROLE表中所有的角色信息;
			List<Role> rolesT = dao2.findAllRoles();
			//为了满足下拉选项中的"全部"这个选项的功能,这里需要临时增加这一项;
			Role allRole = new Role();
			allRole.setId("-1");
			allRole.setName("全部");
			rolesT.add(allRole);
			//为LIST中对象按照ID排序;
			Collections.sort(rolesT,new Comparator<Role>(){
				public int compare(Role arg0, Role arg1) {
					return Integer.valueOf(arg0.getId())-Integer.valueOf(arg1.getId());
				}
			});
			roles = rolesT;
			
			//根据privilegeId和roleName包括分页信息来组合查询结果;
			admins = dao.findByCondition(privilegeId,roleId,page,pageSize);
			
			//查询总页数;
			totalPages = dao.findTotalPages(privilegeId, roleId, pageSize);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		
		return "success";
	}
	
	public String getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
