package com.netctoss.dao.cost;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.pojo.Cost;
import com.netctoss.util.HibernateUtil;

public class HibernateCostDao implements ICostDao{
	
	//根据Cost对象为数据库添加一条资费数据;
	public void addCost(Cost cost) throws DAOException {
		Transaction ts = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			ts = session.beginTransaction();
			session.save(cost);
			ts.commit();
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			e.printStackTrace();
			throw new DAOException("新增资费数据失败",e);
		}
	}
	
	//删除资费数据(由于SERVICE表的COST_ID对此表的ID有外键限制,所以在删除时可能会出现"ORA-02292: integrity constraint (SYSTEM.SERVICE_COST_ID_FK) violated");
	public void deleteById(int id) throws DAOException {
		Cost cost = new Cost();
		cost.setId(id);
		Transaction ts = null;
		Session session = null;
		try{
		session = HibernateUtil.getSession();
		ts = session.beginTransaction();
		session.delete(cost);
		ts.commit();
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			e.printStackTrace();
			throw new DAOException("删除资费数据失败",e);
		}
	}
	
	//查询所有Cost记录并返回一个cost对象列表(每行记录代表一个cost对象);
	public List<Cost> findAll() throws DAOException {
		String hql = "from Cost";
		Session session = null;
		Query query = null;
		try{
			session = HibernateUtil.getSession();
			query = session.createQuery(hql);
			List<Cost> list = query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("查询所有资费数据失败",e);
		}
	}
	
	//根据id进行查询的SQL;
	public Cost findById(Integer id) throws DAOException {
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Cost cost = (Cost)session.load(Cost.class, id);
		return cost;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("根据ID查询资费数据失败",e);
		}
	}
	
	//根据name进行查询的SQL;
	public Cost findByName(String name) throws DAOException {
		String hql = "from Cost where name=? ";
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setString( 0, name);
			//如果hql执行最多返回一条记录;
			Cost cost = (Cost)query.uniqueResult();
			return cost;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("根据NAME查询资费数据失败",e);
		}
	}
	
	//根据name进行查询的SQL,排除了当前id行;
	public Cost findByNameForUpdate(String name, Integer id)
			throws DAOException {
		String hql = "from Cost where name=? and id!=? ";
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			query.setString(0, name);
			query.setInteger(1, id);
			Cost cost = (Cost)query.uniqueResult();
			return cost;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("根据NAME和ID查询资费数据失败",e);
		}
	}
	
	//分页查找记录;
	public List<Cost> findByPage(int page, int pageSize) throws DAOException {
		String hql = "from Cost";
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			//设置分页查询参数;
		 	int begin = (page-1)*pageSize;
			query.setFirstResult(begin); //从哪里开始抓取;
			query.setMaxResults(pageSize); //最大抓取数量;
			List<Cost> list = query.list();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("查询所有资费数据失败",e);
		}
	}
	
	//查询总页数;
	public int findTotalPage(int pageSize) throws DAOException {
		String hql = "select count(*) from Cost";
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Query query = session.createQuery(hql);
			Long totalSize = (Long)query.uniqueResult();
			if(totalSize%pageSize == 0){
				return totalSize.intValue()/pageSize;
			}else{
				return totalSize.intValue()/pageSize+1;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("查询总页数数据失败",e);
		}
	}
	
	//根据cost对象将其中属性更新到数据库;
	public void updateCost(Cost cost) throws DAOException {
		Transaction ts = null;
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			ts = session.beginTransaction();
			session.update(cost);
			ts.commit();
		}catch(Exception e){
			if(ts!=null){
				ts.rollback();
			}
			e.printStackTrace();
			throw new DAOException("更新资费数据失败",e);
		}
	}
	
	public static void main(String[] args){
		ICostDao dao = DAOFactory.getCostDAO();
		try {
			dao.findByPage(1, 5);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
