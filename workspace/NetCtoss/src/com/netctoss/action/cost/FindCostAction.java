package com.netctoss.action.cost;

import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.cost.ICostDao;
import com.netctoss.pojo.Cost;

//用来封装业务流程:数据+算法;
//根据输入来计算输出;
public class FindCostAction {
	//需要注意的是,一下的输入输出属性只是根据其基本功能来划分的,而实际上只要有getter/setter方法,这些
	//定义后的属性既是输入属性又是输出属性,也就是说从客户端传入的参数被Struts2接收赋值到action后,在
	//之后的发送给客户端的请求中也会将这个属性再次以输出属性发送过去;
	
	//输入属性;
	private int page=1; //当前页;这里赋值为1是为了让第一次访问此页面时能够显示第一页的内容;
	
	private int pageSize=5; //页容量;
	

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


	//只需要这样声明一个需要发送给view层的"输出属性",并给出getter/setter方法,
	//struts2就会在execute方法返回后将这个"输出属性"发送给view层;
	private List<Cost> costList; 
	
	private int totalPage; //总页数;
	
	
	public List<Cost> getCostList() {
		return costList;
	}

	public void setCostList(List<Cost> costList) {
		this.costList = costList;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	

	public String execute(){
		
		//1,调用DAO,查询出全部的数据;
		ICostDao dao = DAOFactory.getCostDAO();
		try {
		//2,把数据发送到页面; 
			//costList= dao.findAll();  //此处struts2将自动把赋给costList的内容作为请求内容发送给view层;
			costList = dao.findByPage(page, pageSize); //此处的page,pageSize属性Struts2已经从页面的请求中取得并赋值到了action类中的相应属性里,所以此处可以直接调用;
			totalPage = dao.findTotalPage(pageSize);
		} catch (DAOException e) {
			e.printStackTrace();
			return "error";
		}
		
		//3,返回result的值,使得前端控制器可以通过result找到对应的jsp;
		return "success";
		
	}
}
