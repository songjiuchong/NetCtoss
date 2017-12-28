package com.netctoss.dao.role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.pojo.Privilege;
import com.netctoss.pojo.Role;
import com.netctoss.util.DBUtil;
import com.netctoss.util.PrivilegeReader;
import com.netctoss.vo.RoleVo;

public class RoleDaoImpl implements IRoleDao{
	
	//根据页码来分页查询角色视图记录;
	public List<RoleVo> findByPage(int page,int pageSize) throws DAOException {
		//构造SQL语句;(为了按照ID大小分页显示,这里只能先将整张ROLE表order by ID,因为rownum的优先级高于order by不能在rownum后使用order by);
		String sql = "select * from (select r.*,rownum n from (select * from ROLE order by ID) r where rownum<?) where n>?";
		
		int nextIndex = page*pageSize+1;
		int prevIndex = (page-1)*pageSize;
		
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,nextIndex);
			ps.setInt(2,prevIndex);
			ResultSet rs = ps.executeQuery();
			//根据结果集构造RoleVo视图对象列表;
			List<RoleVo> roles = new ArrayList<RoleVo>();
			
			List<Integer> tempId = new ArrayList<Integer>();
			while(rs.next()){
				tempId.add(rs.getInt("ID"));
			}
			if(tempId!=null&&tempId.size()>0){
				for(Integer id:tempId){
				Role role = findById(id);
				RoleVo roleVo = new RoleVo();
				roleVo.setId(role.getId());
				roleVo.setName(role.getName());
				String[] privilegeIds =  role.getPrivilegeIds();
				if(privilegeIds!=null&&privilegeIds.length!=0){
				List<Privilege> privileges = new ArrayList<Privilege>();
				for(String pId:privilegeIds){
					String privilegeName = PrivilegeReader.getPrivilegeNameById(pId);
					Privilege p = new Privilege();
					p.setId(pId);
					p.setName(privilegeName);
					privileges.add(p);
				}
				
				roleVo.setPrivileges(privileges);
				}
				
				roles.add(roleVo);
				}
			}
			return roles;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("根据页码分页查询角色记录失败！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//获取总页数;
	public int findTotalPage(int pageSize) throws DAOException {
		String sql = "select count(*) from ROLE ";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int total = rs.getInt(1);
			int totalPages = total%pageSize==0? (total/pageSize):(total/pageSize+1);
			return totalPages;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("查询总页数失败！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据Role对象向数据库添加角色,和角色权限记录;
	public void addRole(Role role) throws DAOException {
		if(role == null)
			return;
		
		//1,新增角色信息;
		String sql="insert into ROLE(ID,NAME) values(role_seq.nextval,?)";//参考role_seq.sql;
		String[] columns = {"id"};
		try {
			Connection con = DBUtil.getConnection();
			con.setAutoCommit(false); //事务控制;
			PreparedStatement ps = con.prepareStatement(sql,columns); //回调一个数据库完成update后columns列数组指定的数据;
			ps.setObject(1,role.getName());
			ps.executeUpdate();
			
			//2,取得新增角色信息生产的ID;
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int roleId = rs.getInt(1);
			
			//3,根据生产的ID以及参数中的一组权限ID,更新ROLE_PRIVILEGE表;
			String sql2 = "insert into ROLE_PRIVILEGE(ROLE_ID,PRIVILEGE_ID) values(?,?)";
			PreparedStatement ps2 =con.prepareStatement(sql2);
			String[] privilegeIds = role.getPrivilegeIds();
			if(privilegeIds != null&& privilegeIds.length>0){
				for(String privilegeId:privilegeIds){
					ps2.setObject(1,roleId);
					ps2.setObject(2,Integer.valueOf(privilegeId));
					ps2.addBatch(); //批处理;
					}
				ps2.executeBatch();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DAOException("新增角色信息失败！",e);
		} finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据角色ID查询数据库返回一个Role对象;
	public Role findById(Integer id) throws DAOException {
		if(id == null)
			return null;
		try {
			//1,根据ID查询对应的橘色信息;
			Connection con = DBUtil.getConnection();
			String sql = "select * from ROLE where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Role role = createRole(rs);
			
			//2,根据角色ID查询角色权限数据;
			String sql2="select PRIVILEGE_ID from ROLE_PRIVILEGE where ROLE_ID=?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1,id);
			ResultSet rs2 = ps2.executeQuery();
			List<String> privilegeList = new ArrayList<String>();
			while(rs2.next()){
				privilegeList.add(rs2.getString(1));
			}

			//3,合并1,2查询得到的数据;
			String[] privilegeIds = privilegeList.toArray(new String[0]);
			role.setPrivilegeIds(privilegeIds);
			
			return role;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("根据ID查询角色信息失败",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据查询的结果构建一个Role对象;
	private Role createRole(ResultSet rs) throws SQLException{
		Role r = new Role();
			r.setId(String.valueOf(rs.getInt("ID")));
			r.setName(rs.getString("NAME"));
			return r;
	}
	
	//根据一个Role对象更新其信息;
	public void updateRole(Role role) throws DAOException {
		if(role == null)
			return;
		try {
			Connection con = DBUtil.getConnection();
			con.setAutoCommit(false);
			//1,更新角色信息数据;
			String sql = "update ROLE set NAME=? where ID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,role.getName());
			ps.setInt(2,Integer.valueOf(role.getId()));
			ps.executeUpdate();
			
			//2,删除该角色对应的角色权限数据;
			String sql2 = "delete from ROLE_PRIVILEGE where ROLE_ID=?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1,Integer.valueOf(role.getId()));
			ps2.executeUpdate();
			
			//3,重新插入一组角色权限数据;
			String sql3 = "insert into ROLE_PRIVILEGE(ROLE_ID,PRIVILEGE_ID) values(?,?)";
			PreparedStatement ps3=con.prepareStatement(sql3);
			String[] privilegeIds = role.getPrivilegeIds();
			if(privilegeIds != null&& privilegeIds.length>0){
				for(String privilegeId:privilegeIds){
					ps3.setObject(1,role.getId());
					ps3.setObject(2,Integer.valueOf(privilegeId));
					ps3.addBatch(); //批处理;
					}
				ps3.executeBatch();
			}
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DAOException("更新角色信息失败！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据id删除角色信息和对应的权限信息;
	public void deleteRole(Integer id) throws DAOException {
		
		try {
			Connection con = DBUtil.getConnection();
			con.setAutoCommit(false);
			//根据id删除角色信息;
			String sql = "delete from ROLE where ID=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1,id);
			ps.executeUpdate();
			//根据id删除角色id对应的所有权限信息;
			String sql2 = "delete from ROLE_PRIVILEGE where ROLE_ID=? ";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setObject(1,id);
			ps2.executeUpdate();
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DAOException("删除角色信息和对应的权限信息失败！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据roleId和roleName调用DAO查找是否存在除roleId所代表的角色名外是否还存在与roleName相同的角色名;
	public Role validateRoleName(int roleId, String roleName)
			throws DAOException {
		String sql = "select * from ROLE where NAME=? and ID!=? ";
		if(roleName==null||roleName=="")
			return null;
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1,roleName);
			ps.setObject(2,roleId);
			ResultSet rs = ps.executeQuery();
			Role role = null;
			while(rs.next()){
			role = new Role();
			role.setId(String.valueOf(rs.getInt("ID")));
			role.setName(rs.getString("NAME"));
			}
			return role;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("检查是否存在相同角色名发生错误！",e);
		}
	}
	
	//(Alternative Method);
	//通过表连接的方式来查询角色与对应的角色权限ID,也避免了在循环中调用DAO;
	public List<Role> findRoles(int page,int pageSize) throws DAOException {
		try {
			Connection con = DBUtil.getConnection();
			String sql = "select * from ROLE r inner join ROLE_PRIVILEGE p on r.ID=p.ROLE_ID where r.ID in (" +
					"select ID from(select l.ID,rownum n from ROLE l where rownum<?) where n>? )"; //由于之后用的的MAP存储的特性,此处使用order by ID无效;
			int nextIndex = page*pageSize+1;
			int prevIndex = (page-1)*pageSize;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,nextIndex);
			ps.setInt(2,prevIndex);
			ResultSet rs = ps.executeQuery();
			//由于此关联查询的结果中ROLE的ID将因为不同的PRIVILEGE_ID而出现重复,这里通过MAP封装id与role对象来保证相同ID作为一个KEY对应一个role;
			Map<Integer,Role> roleMap= new HashMap<Integer,Role>();
			//封装权限Id的Map,key是角色ID,values是该角色对应的权限ID数组;
			Map<Integer,List<String>> privilegeMap = new HashMap<Integer,List<String>>();
			//目的是最后两个MAP中key都是不重复的角色id,一个value是role对象,另一个是权限id的数组,
			//然后通过遍历key就能够将对应的权限id数组赋值给role对象了;
			
			//根据每一条结果集来封装两张Map(结果集中会出现相同的id,name,但是privilegeId不会重复);
			while(rs.next()){
				//通过结果集构建roleMap;
				Role role = createRole(rs);
				if(!roleMap.containsKey(role.getId())){
					roleMap.put(Integer.valueOf(role.getId()),role);
				}
				//通过结果集构建privilegeMap,如果结果集中的roleID已经放入了privilegeMap的key中则把查询到的权限ID加入value对应的数组中;
				//如果没有,创建新的数组,将这次得到的roleID和数组一同放入privilegeMap中;
				String privilegeId = rs.getString("PRIVILEGE_ID");
				if(privilegeMap.containsKey(role.getId())){
					List<String> privilegeIds = privilegeMap.get(role.getId());
					privilegeIds.add(privilegeId);
				}else{
					List<String>privilegeIds = new ArrayList<String>();
					privilegeIds.add(privilegeId);
					privilegeMap.put(Integer.valueOf(role.getId()),privilegeIds);
				}
			}
			
			//通过roleId遍历封装Role的Map,将封装privilegeIds的Map中对应的权限ID列表取出,转化为数组后赋值给role对象,再
			//将Role对象放入roles集合中;
			List<Role> roles = new ArrayList<Role>();
			Iterator<Integer> it = roleMap.keySet().iterator();
			while(it.hasNext()){
				Integer roleId = it.next();
				Role role = roleMap.get(roleId);
				List<String> privilegeIdList = privilegeMap.get(roleId);
				String[] privilegeIds = privilegeIdList.toArray(new String[privilegeIdList.size()]);
				role.setPrivilegeIds(privilegeIds);
				roles.add(role);
			}
			
			//按照role对象的id属性对roles集合进行排序,使得显示在页面上时是根据ID从小到大排列的;
			Collections.sort(roles,new Comparator<Role>(){
				public int compare(Role arg0, Role arg1) {
					return Integer.valueOf(arg0.getId())-Integer.valueOf(arg1.getId());
				}
			});
			return roles;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("查找角色与对应的角色权限ID出错！",e);
		}
	}
	
	//查询ROLE表中所有的角色信息;
	public List<Role> findAllRoles() throws DAOException {
		String sql = "select * from ROLE order by ID";
		List<Role> roles = null;
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Role role = createRole(rs);
				if(roles==null)
					roles = new ArrayList<Role>();
				roles.add(role);
			}
			return roles;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("查询ROLE表中所有的角色信息失败！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	
	//单元测试;
	public static void main(String[] args){
		IRoleDao dao = DAOFactory.getIRoleDao();
//		try {
//			List<Role> roles = dao.findRoles(1,5);
//			System.out.println(roles.size());
//			for(Role r:roles){
//				System.out.println(r.getId());
//			}
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
		try {
			dao.findById(100);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
}
