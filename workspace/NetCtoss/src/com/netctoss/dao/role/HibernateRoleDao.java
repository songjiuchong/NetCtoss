package com.netctoss.dao.role;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.netctoss.dao.DAOException;
import com.netctoss.pojo.Admin;
import com.netctoss.pojo.Role;
import com.netctoss.util.HibernateUtil;
import com.netctoss.vo.RoleVo;

public class HibernateRoleDao implements IRoleDao{

	public void addRole(Role role) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public void deleteRole(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public List<Role> findAllRoles() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Role findById(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RoleVo> findByPage(int page, int pageSize) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Role> findRoles(int page, int pageSize) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public int findTotalPage(int pageSize) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void updateRole(Role role) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public Role validateRoleName(int roleId, String roleName)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	//测试:
	public static void main(String [] args){
		
		try {
			Session session = HibernateUtil.getSession();
			Transaction ts = session.beginTransaction();
			Admin admin1 = (Admin)session.load(Admin.class, 73);
			Admin admin2 = (Admin)session.load(Admin.class, 74);
			Admin admin3 = (Admin)session.load(Admin.class, 75);
			session.delete(admin1);
			session.delete(admin2);
			session.delete(admin3);
			ts.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
