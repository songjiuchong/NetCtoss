package com.netctoss.dao.cost;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.pojo.Cost;

public interface ICostDao {
	
	//查找所有记录;
	List<Cost> findAll() throws DAOException;
	
	//根据页码,每页容量来查询当前页的自费记录;
	//page,当前页码;pageSize,每页容量;
	List<Cost> findByPage(int page,int pageSize) throws DAOException;
	
	//根据页容量查询所有数据一共可以分为几页,返回总页数;
	int findTotalPage(int pageSize) throws DAOException;
	
	//根据id删除一条资费数据;
	void deleteById(int id)throws DAOException;
	
	//根据名称查询资费的数据;
	Cost findByName(String name) throws DAOException;
	
	//根据Cost对象为数据库添加一条资费数据;
	void addCost(Cost cost) throws DAOException;
	
	//根据id查询一条资费数据;
	Cost findById(Integer id) throws DAOException;
	
	//根据传入参数进行更新;
	void updateCost(Cost cost) throws DAOException;
	
	//根据传入的name和id来查询资费数据,但是排除了id的项;
	Cost findByNameForUpdate(String name,Integer id)throws DAOException;
	
}
