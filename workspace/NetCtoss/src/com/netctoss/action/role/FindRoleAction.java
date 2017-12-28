package com.netctoss.action.role;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.vo.RoleVo;

public class FindRoleAction {
	
	//输入属性;
	private int page=1; //当前页;
	
	private int pageSize; //页容量;
	
	//输出属性;
	
	private List<RoleVo> roles;
	
	private int totalPages; //总页数;

	
	public String execute(){
		//获得分页后的角色视图集合;
		try {
			IRoleDao dao = DAOFactory.getIRoleDao();
			roles = dao.findByPage(page,pageSize);
			totalPages = dao.findTotalPage(pageSize);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
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

	public List<RoleVo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleVo> roles) {
		this.roles = roles;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
}
