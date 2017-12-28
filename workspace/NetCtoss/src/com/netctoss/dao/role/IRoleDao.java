package com.netctoss.dao.role;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.pojo.Role;
import com.netctoss.vo.RoleVo;

public interface IRoleDao {
	
	//新增角色;
	void addRole(Role role) throws DAOException;
	
	//根据角色ID查询角色数据;
	Role findById(Integer id) throws DAOException;
	
	//更新角色信息;
	void updateRole(Role role) throws DAOException;
	
	//查询总页数;
	int findTotalPage(int pageSize) throws DAOException;
	
	//根据页码查询所有角色信息;
	List<RoleVo> findByPage(int page,int pageSize) throws DAOException;
	
	//根据id删除角色信息和对应的权限信息;
	void deleteRole(Integer id) throws DAOException;
	
	//根据roleId和roleName检查是否存在除roleId所代表的角色名外是否还存在与roleName相同的角色名;
	Role validateRoleName(int roleId,String roleName) throws DAOException;
	
	//查询ROLE表中所有的角色信息;
	List<Role> findAllRoles() throws DAOException;
	
	//(Alternative Method);
	//通过表连接的方式来查询角色与对应的角色权限ID,也避免了在循环中调用DAO;
	List<Role> findRoles(int page,int pageSize) throws DAOException;
}
