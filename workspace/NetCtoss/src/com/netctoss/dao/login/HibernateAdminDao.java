package com.netctoss.dao.login;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.dao.service.IServiceDao;
import com.netctoss.pojo.Admin;
import com.netctoss.pojo.Role;
import com.netctoss.pojo.Service;
import com.netctoss.util.HibernateUtil;

public class HibernateAdminDao implements IAdminDao{

	public void addAdmin(Admin admin) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public Admin findByCodeAndPassword(String adminCode, String password)
			throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Admin> findByCondition(String privilegeId, String roleId,
			int page, int pageSize) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Admin> findByConditions(List<Integer> roleIds, int page,
			int pageSize) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Admin findById(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public Admin findByUserName(String name) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Integer> findRoleIdByPrivilegeIdAndRoleName(String privilegeId,
			String roleName) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	public int findTotalPages(String privilegeId, String roleId, int pageSize)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int findTotalPages(List<Integer> roleIds, int pageSize)
			throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void resetPassward(String[] idsArray) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public void updateAdmin(Admin admin) throws DAOException {
		// TODO Auto-generated method stub
		
	}
	
	//测试:
	public static void main(String [] args){
		try {
			Session session = HibernateUtil.getSession();
			Admin admin = (Admin)session.load(Admin.class,50);
			System.out.println(admin.getName());
			Set<Role> roles = admin.getRoles();
			System.out.println("!!!!!");
			for(Role r:admin.getRoles()){
				for(Admin a:r.getAdmins()){
					System.out.println(a.getName());
				}
				System.out.println(r.getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
