package com.netctoss.dao.account;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.pojo.Account;

public interface IAccountDao {
	
	//根据条件查询账务账号的数据;
	List<Account> findByCondition(
	String idcardNo,String realName,String loginName,String status, int page, int pageSize) 
	throws DAOException;
	
	//根据页容量查询所有数据一共可以分为几页,返回总页数;
	int findTotalPage(String idcardNo, String realName,
	String loginName, String status,int pageSize) throws DAOException;
	
	//根据id,更新此数据的状态为0,同时将暂停日期的字段的值清空;
	void startAccount(int id) throws DAOException;
	
	//根据id,更新此数据的状态为1,同时将暂停日期设置为系统时间;
	void pauseAccount(int id) throws DAOException;
	
	//根据id,更新此数据的状态为2,同时将删除日期设置为系统时间;
	void deleteAccount(int id) throws DAOException;
	
	//根据Account对象为数据库添加一条账务账号数据;
	void addAccount(Account account) throws DAOException;
	
	//根据账务账号名查找一条记录;
	Account findByAccountName(String accountName) throws DAOException;

	//根据身份证查询对应的账务账号;
	Account findAccountByIdCardNo(String idcardNo) throws DAOException;
	
	//根据accountId(也就是ACCOUNT表中的ID)来查找Account对象;
	Account findAccountById(Integer accountId) throws DAOException;
	
	//根据传入参数进行更新Account记录;
	void updateAccount(Account account) throws DAOException;
}
