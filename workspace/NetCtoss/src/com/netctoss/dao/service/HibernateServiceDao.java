package com.netctoss.dao.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.pojo.Service;
import com.netctoss.util.HibernateUtil;
import com.netctoss.vo.ServiceVo;

public class HibernateServiceDao implements IServiceDao{
	
	//根据Service对象为数据库添加一条业务账号数据;
	public void addService(Service service) throws DAOException {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		session.save(service);
		ts.commit();
	}
	
	//根据id,更新此数据的状态为2,同时将删除日期设置为系统时间;
	public void deleteService(int[] id) throws DAOException {
		Session session = HibernateUtil.getSession();
		Transaction ts = session.beginTransaction();
		for(int i = 0;i<id.length;i++){
			Service service = (Service)session.load(Service.class, id[i]);
			service.setStatus("2");
			service.setCloseDate(new Date());
			session.update(service);
		}
		ts.commit();
	}
	
	//根据条件查询业务账号的数据;
	public List<ServiceVo> findByCondition(String osUserName, String unixHost,
			String idCardNo, String status, int page, int pageSize)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	//根据id查询一条业务账号数据;
	public Service findById(Integer id) throws DAOException {
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Service service = (Service)session.load(Service.class, id);
			return service;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("根据ID查找Service对象失败！",e);
		}
	}
	
	//根据业务帐号ID生成一个业务帐号视图对象;
	public ServiceVo findByServiceId(Integer serviceId) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	//根据账务账号ID查询所有业务账号;
	public List<Service> findServiceByAccountId(int accountId)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	//根据页容量查询所有数据一共可以分为几页,返回总页数;
	public int findTotalPage(String osUserName, String unixHost,
			String idCardNo, String status, int pageSize) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//根据id,将指定的业务账号状态更新为1,同时将暂停日期设置为系统时间;
	public void pauseService(int[] id) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	//根据id,更新此数据的状态为0,同时将暂停日期的字段的值清空;
	public void startService(int id) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	//根据传入参数进行更新;
	public void updateService(Service service) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	//测试:
	public static void main(String [] args){
		IServiceDao dao = DAOFactory.getIServiceDao();
		String hql = "from Service where osUsername =?";
		try {
			Session session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setString(0,"weixb");
			List<Service> list = query.list();
			for(Service s:list){
				System.out.println(s.getAccount().getId());
				System.out.println(s.getServiceId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
