package com.netctoss.dao.service;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.pojo.Service;
import com.netctoss.vo.ServiceVo;

public interface IServiceDao {
	
	//根据条件查询业务账号的数据;
	List<ServiceVo> findByCondition(
	String osUserName,String unixHost,String idCardNo,String status, int page, int pageSize) 
	throws DAOException;
	
	//根据业务帐号ID生成一个业务帐号视图对象;
	ServiceVo findByServiceId(Integer serviceId) throws DAOException;
	
	//根据页容量查询所有数据一共可以分为几页,返回总页数;
	int findTotalPage(
	String osUserName,String unixHost,String idCardNo,String status,int pageSize) 
	throws DAOException;
	
	//根据Service对象为数据库添加一条业务账号数据;
	void addService(Service service) throws DAOException;
	
	//根据id,更新此数据的状态为0,同时将暂停日期的字段的值清空;
	void startService(int id) throws DAOException;
	
	//根据id,将指定的业务账号状态更新为1,同时将暂停日期设置为系统时间;
	void pauseService(int[] id) throws DAOException;
	
	//根据id,更新此数据的状态为2,同时将删除日期设置为系统时间;
	void deleteService(int[] id) throws DAOException;
	
	//根据id查询一条业务账号数据;
	Service findById(Integer id) throws DAOException;
	
	//根据传入参数进行更新;
	void updateService(Service service) throws DAOException;

	//根据账务账号ID查询所有业务账号;
	List<Service> findServiceByAccountId(int accountId) throws DAOException;
}
