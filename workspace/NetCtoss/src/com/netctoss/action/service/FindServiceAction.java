package com.netctoss.action.service;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.vo.ServiceVo;

public class FindServiceAction {
	
	//输入属性;
	private String osUserName;

	private String unixHost;
	
	private String idcardNo;

	private String status;
	
	private int page=1; //当前页;
	
	private int pageSize; //页容量;
	
	//输出属性;
	private List<ServiceVo> serviceVos;
	
	private int totalPages; //总页数;
	
	public String execute(){
		//根据输入属性,调用DAO,查询结果集;
		IServiceDao serviceDao = DAOFactory.getIServiceDao();
		try {
			serviceVos = serviceDao.findByCondition(osUserName, unixHost, idcardNo, status, page, pageSize);
			totalPages = serviceDao.findTotalPage(osUserName, unixHost, idcardNo, status, pageSize);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		//返回success,跳转到查询界面;
		return "success";
	}
	
	public String getOsUserName() {
		return osUserName;
	}

	public void setOsUserName(String osUserName) {
		this.osUserName = osUserName;
	}

	public String getUnixHost() {
		return unixHost;
	}

	public void setUnixHost(String unixHost) {
		this.unixHost = unixHost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<ServiceVo> getServiceVos() {
		return serviceVos;
	}

	public void setServiceVos(List<ServiceVo> serviceVos) {
		this.serviceVos = serviceVos;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
	
	public String getIdcardNo() {
		return idcardNo;
	}

	public void setIdcardNo(String idcardNo) {
		this.idcardNo = idcardNo;
	}
	
}
