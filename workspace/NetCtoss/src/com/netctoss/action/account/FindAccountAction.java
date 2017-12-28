package com.netctoss.action.account;

import java.util.List;

import com.netctoss.action.BaseAction;
import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.pojo.Account;

public class FindAccountAction extends BaseAction{
	
	//输入属性;
	private String idcardNo;
	
	private String realName;
	
	private String loginName; 
	
	private String status;
	
	private int page=1; //当前页;
	
	private int pageSize; //页容量;
	

	//输出属性;
	private List<Account> accounts;
	
	private int totalPages; //总页数;
	
	
	public String execute(){
		//根据输入属性,调用DAO,查询结果集;
		IAccountDao accountDao = DAOFactory.getIAccountDao();
		try {
			accounts = accountDao.findByCondition(idcardNo, realName, loginName, status, page, pageSize);
			totalPages = accountDao.findTotalPage(idcardNo, realName, loginName, status, pageSize);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,跳转到查询界面;
		return "success";
	}

	
	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}
	
}
