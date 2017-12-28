package com.netctoss.dao.login;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.pojo.Admin;

public interface IAdminDao {
	
	//根据账号,密码查询用户;
	Admin findByCodeAndPassword(String adminCode,String password) throws DAOException;
	
	//根据用户名,查询是否存在;
	Admin findByUserName(String name) throws DAOException;
	
	//根据管理员ID,获得一个管理员对象;
	Admin findById(Integer id) throws DAOException;
	
	//根据privilegeId和roleName包括分页信息来组合查询结果;
	List<Admin> findByCondition(String privilegeId,String roleId,int page,int pageSize) throws DAOException;
	
	//查询总页数;
	int findTotalPages(String privilegeId,String roleId,int pageSize) throws DAOException;
	
	//将所有选中的管理员密码重置;
	void resetPassward(String[] idsArray) throws DAOException;
	
	//新增管理员;
	void addAdmin(Admin admin) throws DAOException;
	
	//更新管理员信息;
	void updateAdmin(Admin admin) throws DAOException;
	
	//根据ID删除管理员信息;
	void deleteById(Integer id) throws DAOException;
	
	//alternative;
	//根据privilegeId,roleName和分页信息查询管理员对象集合;
	List<Admin> findByConditions(List<Integer> roleIds,int page,int pageSize) throws DAOException;
	
	//alternative;
	//根据privilegeId和roleName查询所有符合条件的ROLE对象Id;
	List<Integer> findRoleIdByPrivilegeIdAndRoleName(String privilegeId,String roleName) throws DAOException;
	
	//alternative;
	//根据所有符合条件的ROLE对象Id和pageSize来获得总页数;
	int findTotalPages(List<Integer> roleIds,int pageSize) throws DAOException;
}
