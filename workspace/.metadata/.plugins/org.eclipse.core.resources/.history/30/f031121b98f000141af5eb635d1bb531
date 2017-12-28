package com.netctoss.dao;

import com.netctoss.dao.account.AccountDaoImpl;
import com.netctoss.dao.account.HibernateAccountDao;
import com.netctoss.dao.account.IAccountDao;
import com.netctoss.dao.cost.CostDaoImpl;
import com.netctoss.dao.cost.HibernateCostDao;
import com.netctoss.dao.cost.ICostDao;
import com.netctoss.dao.login.AdminDaoImpl;
import com.netctoss.dao.login.HibernateAdminDao;
import com.netctoss.dao.login.IAdminDao;
import com.netctoss.dao.role.IRoleDao;
import com.netctoss.dao.role.RoleDaoImpl;
import com.netctoss.dao.service.HibernateServiceDao;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.dao.service.ServiceDaoImpl;

//DAO工厂类;
public class DAOFactory {
	
	//为了节省资源不用每次在调用CostDaoImpl中方法时都创建一个新的对象,
	//这里利用单例方法静态声明了一个对象,以供调用;
	private static ICostDao costDao = new HibernateCostDao(); //new CostDaoImpl();
	
	//返回ICostDat接口的实例;
	public static ICostDao getCostDAO(){
		return costDao;
	}
	
	private static IAdminDao adminDao = new AdminDaoImpl(); //new HibernateAdminDao();
	
	public static IAdminDao getAdminDao(){
		return adminDao;
	}
	
	private static IAccountDao accountDao = new HibernateAccountDao();//new AccountDaoImpl();
	
	public static IAccountDao getIAccountDao(){
		return accountDao;
	}
	
	private static IServiceDao serviceDao = new  HibernateServiceDao();//new ServiceDaoImpl();
	
	public static IServiceDao getIServiceDao(){
		return serviceDao;
	}
	
	private static IRoleDao roleDao = new RoleDaoImpl(); //new HibernateRoleDao();
	
	public static IRoleDao getIRoleDao(){
		return roleDao;
	}
	
}
