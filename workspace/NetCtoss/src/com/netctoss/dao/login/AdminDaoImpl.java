package com.netctoss.dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.netctoss.dao.DAOException;
import com.netctoss.pojo.Admin;
import com.netctoss.util.DBUtil;

public class AdminDaoImpl implements IAdminDao {
	
	//根据用户名和密码查询用户信息的SQL;
	private String findByCodeAndPasswordSql = "select * from ADMIN_INFO where ADMIN_CODE=? and PASSWORD=?";
	
	public Admin findByCodeAndPassword(String adminCode, String password)
			throws DAOException {
		
		//先校验参数;
		if(adminCode==null||password==null)
		return null;
		
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(findByCodeAndPasswordSql);
			ps.setString(1, adminCode);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			Admin a = createAdmin(rs);
			return a;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("查询用户失败",e);
		}finally{
			DBUtil.closeConnection();
		}
		return null;
	}
	
	//将数据库返回的ResultSet中的每条信息存入pojo属性的方法;
	private Admin createAdmin(ResultSet rs) throws SQLException{
		Admin a =new Admin();
		a.setId(rs.getInt("ID"));
		a.setAdminCode(rs.getString("ADMIN_CODE"));
		a.setPassword(rs.getString("PASSWORD"));
		a.setName(rs.getString("NAME"));
		a.setEmail(rs.getString("EMAIL"));
		a.setTelephone(rs.getString("TELEPHONE"));
		a.setEnrollDate(rs.getDate("ENROLLDATE"));
		return a;
	}
	
	//根据用户名查询此用户是否存在的SQL;
	private String findByUserNameSql="select * from ADMIN_INFO where ADMIN_CODE=?";
	
	public Admin findByUserName(String name) throws DAOException {
		//由于在js中已经校验过输入的用户名不为空,为空后无法运行到异步校验,所以此处无效检查;
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(findByUserNameSql);
			ps.setString(1,name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			Admin a = createAdmin(rs);
			return a;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("查询用户失败",e);
		}finally{
			DBUtil.closeConnection();
		}		
		return null;
	}
	
	//根据privilegeId和roleName包括分页信息来组合查询结果,建立在findAdmin页面中通过
	//角色下拉列表传递的ROLE对象ID来搜索;
	//注意1:此处的方法由于之前已经写好主体但是逻辑上有问题后做了修改,所以功能正确,但是
	//不够简化,最简化的方式是在根据条件查询出所有满足条件的管理员ID后再次做查询,但是
	//第二次查询就不需要链接ROLE_PRIVILEGE这张表了,因为第二次查询是为了获取管理员对象
	//对应的所有角色名字符串,不需要权限信息;最后的第三次查询是为了分页,然后将最终的
	//需要被显示在findAdmin页面上的admin对象的结果集逐个创建admin对象,并根据第二次查询
	//得到的和管理员对象对应的角色链接字符串来给admin对象中的adminNames属性赋值;
	//注意2:在一些需要去重的操作中可以根据情况使用distinct来去重,而不用之后再用java代码
	//来实现;
	
	public List<Admin> findByCondition(String privilegeId, String roleId,
			int page, int pageSize) throws DAOException {
		
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select ai.ID,ri.NAME from ADMIN_INFO ai "+
					 "inner join ADMIN_ROLE ar on ai.ID = ar.ADMIN_ID "+
					 "inner join ROLE ri on ri.ID = ar.ROLE_ID "+
					 "inner join ROLE_PRIVILEGE rp on rp.ROLE_ID = ri.ID "+
					 "where 1=1 ");
		if(privilegeId!=null && privilegeId.length()>0&&!privilegeId.equals("-1")){
			sb.append("and rp.PRIVILEGE_ID=? ");
			params.add(privilegeId);
		}
		if(roleId!=null && roleId.length()>0&&!roleId.equals("-1")){
			sb.append("and ar.ROLE_ID=? ");
			Integer roleIdT = Integer.parseInt(roleId);
			params.add(roleIdT);
		}
		String sql = sb.toString();
		
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				ps.setObject(i+1,params.get(i));
			}
			ResultSet rs = ps.executeQuery();
			//获得符合条件的不重复管理员ID;(这里其实可以通过在之前sql中加distinct来去重);
			List<Integer> adminIdsx = null;
			while(rs.next()){
				if(adminIdsx==null)
					adminIdsx = new ArrayList<Integer>();
				Integer adminId = rs.getInt("ID");
				if(!adminIdsx.contains(adminId))
					adminIdsx.add(adminId);
			}
			if(adminIdsx==null||adminIdsx.size()==0)
				return null;
			
			StringBuffer sb2 = new StringBuffer();
			sb2.append("select ai.ID,ri.NAME from ADMIN_INFO ai "+
					 "inner join ADMIN_ROLE ar on ai.ID = ar.ADMIN_ID "+
					 "inner join ROLE ri on ri.ID = ar.ROLE_ID "+
					 "inner join ROLE_PRIVILEGE rp on rp.ROLE_ID = ri.ID "+
					 "where ai.ID in (");
			String sqlx = sb2.toString();
			Integer[] adminIdsxT = adminIdsx.toArray(new Integer[adminIdsx.size()]);
			for(int i=0;i<adminIdsxT.length;i++){
				sqlx+= i==0? adminIdsxT[i]:(","+adminIdsxT[i]);
			}
			sqlx+=")";
			PreparedStatement psx = con.prepareStatement(sqlx);
			ResultSet rsx = psx.executeQuery();
			
			//由于结果集中会出现重复的ADMIN记录,这里将符合条件的不重复的ADMIN对象的ID放入一个集合;
			//为了之后在findAdmin页面显示角色名连接字符串,此处需要将查询出的每一个角色名与相应的ADMIN对象ID绑定;
			Map<Integer,String> adminIds = null;
			//又由于相同的ADMIN对象ID可能对应了相同的角色名(表连接后相同角色对应不同权限),又可能对应了不同的角色名(一个ADMIN对象可以对应多个不同角色),
			//那就需要另一个集合用来区分同一个ADMIN对象ID所对应的角色名连接字符串中是否已经将出现过的角色名加入进去了,就需要这个集合来将已经加入角色名字符串
			//的角色名放入,用来验证避免重复加入字符串;
			Map<Integer,List<String>> checkRoleName = null;
			//List<String> check = null;
			while(rsx.next()){
				Integer adminId = rsx.getInt("ID");
				String roleName = rsx.getString("NAME");
				if(adminIds==null){
					adminIds = new HashMap<Integer,String>();
					checkRoleName = new HashMap<Integer,List<String>>();
				}
				if(!adminIds.containsKey(adminId)){
					String roleNames ="";
					checkRoleName.put(adminId, new ArrayList<String>());
					if(roleName==null)
						roleName="";
					roleNames+=roleName;
					List<String> temp = checkRoleName.get(adminId);
					temp.add(roleName);
					checkRoleName.put(adminId,temp);
					adminIds.put(adminId,roleNames);
				}else{
					String roleNames = adminIds.get(adminId);
					if(roleName==null)
						roleName="";
					if(!checkRoleName.get(adminId).contains(roleName)){
						roleNames+=(","+roleName);
						List<String> temp = checkRoleName.get(adminId);
						temp.add(roleName);
						checkRoleName.put(adminId,temp);
						adminIds.put(adminId,roleNames);
					}
				}
			}
			
			//根据查询出的符合条件且不重复的ADMIN对象ID来构建分页查询的sql;
			sql = "select i.*,rownum n from (select * from ADMIN_INFO order by ID) i where ";
			if(adminIds!=null&&adminIds.size()!=0){
				sql+="i.ID in (";
				Set<Integer> adminIdsSet = adminIds.keySet();
				Integer[] adminIdsT = adminIdsSet.toArray(new Integer[adminIdsSet.size()]);
				for(int i=0;i<adminIdsT.length;i++){
					sql+= i==0? adminIdsT[i]:(","+adminIdsT[i]);
				}
				sql+=") and ";
			}else{
				return null;
			}
			sql+="rownum<? ";
			String sql2 = "select * from (";
			String sql3 = ") where n>? ";
			sql = sql2+sql+sql3;
			int minIndex = (page-1)*pageSize;
			int maxIndex = page*pageSize+1;
			List<Admin> admins = null;
			PreparedStatement ps2 = con.prepareStatement(sql);
			ps2.setInt(1,maxIndex);
			ps2.setInt(2,minIndex);
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
				if(admins==null)
					admins=new ArrayList<Admin>();
				Admin admin = createAdmin(rs2);
				//将之前在查询符合条件的Admin对象ID时记录在和ID对应的MAP中的角色名连接字符串取出赋值给Admin对象;
				String roleNames = adminIds.get(admin.getId());
				admin.setRoleNames(roleNames);
				admins.add(admin);
			}
			return admins;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("根据privilegeId和roleName包括分页信息来组合查询结果错误",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//查询总页数;
	public int findTotalPages(String privilegeId, String roleId, int pageSize)
			throws DAOException {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("select ai.ID from ADMIN_INFO ai "+
					 "inner join ADMIN_ROLE ar on ai.ID = ar.ADMIN_ID "+
					 "inner join ROLE ri on ri.ID = ar.ROLE_ID "+
					 "inner join ROLE_PRIVILEGE rp on rp.ROLE_ID = ri.ID "+
					 "where 1=1 ");
		if(privilegeId!=null && privilegeId.length()>0&&!privilegeId.equals("-1")){
			sb.append("and rp.PRIVILEGE_ID=? ");
			params.add(privilegeId);
		}
		if(roleId!=null && roleId.length()>0&&!roleId.equals("-1")){
			sb.append("and ar.ROLE_ID=? ");
			params.add(roleId);
		}
		String sql = sb.toString();
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				ps.setObject(i+1,params.get(i));
			}
			ResultSet rs = ps.executeQuery();
			//由于结果集中会出现重复的ADMIN记录,这里将符合条件的不重复的ADMIN对象的ID放入一个集合;
			List<Integer> adminIds = null;
			while(rs.next()){
				Integer adminId = rs.getInt("ID");
				if(adminIds==null)
					adminIds = new ArrayList<Integer>();
				if(!adminIds.contains(adminId)){
					adminIds.add(adminId);
				}
			}
			
			//根据查询出的符合条件且不重复的ADMIN对象ID来构建查询总页面的sql;
			sql = "select count(*) from ADMIN_INFO i where ";
			if(adminIds!=null&&adminIds.size()!=0){
				sql+="i.ID in (";
				Integer[] adminIdsT = adminIds.toArray(new Integer[adminIds.size()]);
				for(int i=0;i<adminIdsT.length;i++){
					sql+= i==0? adminIdsT[i]:(","+adminIdsT[i]);
				}
				sql+=")";
			}else{
				sql+="1=9 ";
			}
			PreparedStatement ps2 = con.prepareStatement(sql);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			int rows = rs2.getInt(1);
			int totalPages = rows%pageSize==0? (rows/pageSize):(rows/pageSize+1);
			return totalPages;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("查询总页数时错误！",e);
			}finally{
				DBUtil.closeConnection();
			}
	}
	
	//alternative;(待测试);建立在findAdmin页面中通过输入角色名来搜索;
	//与step2,step3一起共分为三步来查询结果,逻辑清晰,但不够简洁;
	//step1:根据privilegeId和roleName查询所有符合条件的ROLE对象Id;
	public List<Integer> findRoleIdByPrivilegeIdAndRoleName(String privilegeId,
			String roleName) throws DAOException {
		//构建查询语句;
		String sql="select ID from ROLE r inner join ROLE_PRIVILEGE p on r.ID=p.ROLE_ID where 1=1 ";
		List<Integer> roleIds = new ArrayList<Integer>();
		List<Object> params = new ArrayList<Object>();
		if(privilegeId!=null&&privilegeId!=""&&privilegeId!="-1"){
			sql+="and p.PRIVILEGE_ID=? ";
			Integer pi = Integer.parseInt(privilegeId);
			params.add(pi);
		}
		if(roleName!=null&&roleName!=""){
			sql+="and r.NAME=? ";
			params.add(roleName);
		}
		//查询符合条件的所有ROLE对象ID;
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			for(int i=0;i<params.size();i++){
				ps.setObject(i+1,params.get(i));
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Integer roleId = rs.getInt("ID");
				if(!roleIds.contains(roleId)){
					roleIds.add(roleId);
				}
			}
			return roleIds;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("根据privilegeId和roleName查询所有符合条件的ROLE对象Id错误！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//step2:根据所给的角色ID和分页信息查找Admin对象列表;
	public List<Admin> findByConditions(List<Integer> roleIds,
			int page, int pageSize) throws DAOException {
		
		//根据ROLE角色ID构建出查询所有符合条件的不重复的ADMIN对象ID;
		String sql = "select ID from ADMIN_INFO i inner join ADMIN_ROLE r on i.ID=r.ADMIN_ID where ";
		if(roleIds!=null&&roleIds.size()!=0){
			sql+="r.ROLE_ID in (";
			Integer[] roleIdsT = roleIds.toArray(new Integer[roleIds.size()]);
			for(int i=0;i<roleIdsT.length;i++){
				sql+= i==0? roleIdsT[i]:(","+roleIdsT[i]);
			}
			sql+=")";
		}else{
			return null;
		}
		try {
		Connection con = DBUtil.getConnection();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Integer> adminIds = null;
		while(rs.next()){
			Integer adminId = rs.getInt("ID");
			if(adminIds==null)
				adminIds = new ArrayList<Integer>();
			if(!adminIds.contains(adminId)){
				adminIds.add(adminId);
			}
		}
		//step3:
		//根据查询出的符合条件且不重复的ADMIN对象ID来构建分页查询的sql;
		sql = "select i.*,rownum n from ADMIN_INFO i where ";
		if(adminIds!=null&&adminIds.size()!=0){
			sql+="i.ID in (";
			Integer[] adminIdsT = adminIds.toArray(new Integer[adminIds.size()]);
			for(int i=0;i<adminIdsT.length;i++){
				sql+= i==0? adminIdsT[i]:(","+adminIdsT[i]);
			}
			sql+=") and";
		}else{
			return null;
		}
		sql+="rownum<? ";
		String sql2 = "select * from (";
		String sql3 = ") where n>? order by i.ID ";
		sql = sql2+sql+sql3;
		
		int minIndex = (page-1)*pageSize;
		int maxIndex = page*pageSize+1;
		List<Admin> admins = null;
		PreparedStatement ps2 = con.prepareStatement(sql);
		ps2.setInt(1,maxIndex);
		ps2.setInt(2,minIndex);
		ResultSet rs2 = ps2.executeQuery();
		while(rs2.next()){
			if(admins==null)
				admins=new ArrayList<Admin>();
			Admin admin = createAdmin(rs);
			admins.add(admin);
		}
		return admins;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("根据所给的角色ID和分页信息查找Admin对象列表错误",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//alternative;(待测试);
	//根据所有符合条件的ROLE对象Id和pageSize来获得总页数;
	public int findTotalPages(List<Integer> roleIds, int pageSize)
			throws DAOException {
		//构建查询总页面的sql;
		String sql = "select count(*) from ADMIN_INFO i inner join ADMIN_ROLE r on i.ID=r.ADMIN_ID where ";
		if(roleIds!=null&&roleIds.size()!=0){
			sql+="r.ROLE_ID in (";
			Integer[] roleIdsT = roleIds.toArray(new Integer[roleIds.size()]);
			for(int i=0;i<roleIdsT.length;i++){
				sql+= i==0? roleIdsT[i]:(","+roleIdsT[i]);
			}
			sql+=")";
		}else{
			sql+="1=9 ";
		}
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int rows = rs.getInt(1);
			int totalPages = rows%pageSize==0? (rows/pageSize):(rows/pageSize+1);
			return totalPages;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("查询总页数时错误！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//将所有选中的管理员密码重置;
	public void resetPassward(String[] idsArray) throws DAOException {
		if(idsArray==null||idsArray.length==0){
			return;
		}
		String sql = "update ADMIN_INFO set PASSWORD='123456' where ID in (";
		for(int i = 0;i<idsArray.length;i++){
			if(i==0){
				sql+=idsArray[i];
			}else{
				sql+=","+idsArray[i];
			}
		}
		sql+=")";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("将所有选中的管理员密码重置错误！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//新增管理员;
	public void addAdmin(Admin admin) throws DAOException {
		if(admin==null)
			return;
		try {
			Connection con = DBUtil.getConnection();
			con.setAutoCommit(false);
			//1,插入管理员数据;
			String sql = "insert into ADMIN_INFO values(admin_seq.nextval,?,?,?,?,?,SYSDATE)"; //参考admin_seq.sql;
			String[] columns = {"ID"};
			PreparedStatement ps = con.prepareStatement(sql,columns);
			ps.setString(1,admin.getAdminCode());
			ps.setString(2,admin.getPassword());
			ps.setString(3,admin.getName());
			ps.setString(4,admin.getTelephone());
			ps.setString(5,admin.getEmail());
			ps.executeUpdate();
			
			//2,返回自动生成的管理员ID;
			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			int adminId = rs.getInt(1);
			
			//3,插入管理员角色数据;
			String sql2 = "insert into ADMIN_ROLE values(?,?)";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			String[] roleIds = admin.getRoleIds();
			if(roleIds!=null){
				for(int i=0;i<roleIds.length;i++){
					ps2.setInt(1,adminId);
					ps2.setInt(2,Integer.parseInt(roleIds[i]));
					ps2.addBatch();
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
			throw new DAOException("新增管理员错误！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据管理员ID,获得一个管理员对象;
	public Admin findById(Integer id) throws DAOException {
		try {
			Connection con = DBUtil.getConnection();
			String sql = "select * from ADMIN_INFO where ID=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Admin admin = createAdmin(rs);
			if(admin==null){
				return null;
			}
			//根据管理员ID查找它所对应的所有角色ID数组;
			String sql2 = "select r.ROLE_ID from ADMIN_INFO i inner join ADMIN_ROLE r on r.ADMIN_ID=i.ID where i.ID=? ";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1,id);
			ResultSet rs2 = ps2.executeQuery();
			List<String> roleIds = null;
			String [] roleIdsA = null;
			while(rs2.next()){
				if(roleIds==null)
					roleIds = new ArrayList<String>();
				Integer roleId = rs2.getInt(1);
				roleIds.add(roleId.toString());
			}
			if(roleIds!=null&&roleIds.size()>0){
				roleIdsA = roleIds.toArray(new String[roleIds.size()]);
				admin.setRoleIds(roleIdsA);
			}
			return admin;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("根据管理员ID,获得一个管理员对象错误！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//更新管理员信息;
	public void updateAdmin(Admin admin) throws DAOException {
		if(admin==null){
			return;
		}
		try {
			//根据Admin对象更新ADMIN_INFO表;
			String sql = "update ADMIN_INFO set NAME=?,TELEPHONE=?,EMAIL=? where ID=? ";
			Connection con = DBUtil.getConnection();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,admin.getName());
			ps.setString(2,admin.getTelephone());
			ps.setString(3,admin.getEmail());
			ps.setInt(4,admin.getId());
			ps.executeUpdate();
			
			//根据ADMIN对象中的roleIds数组来更新ADMIN_ROLE表;
			//先删除此ADMIN对象对应的所有角色信息;
			String sql2 = "delete from ADMIN_ROLE where ADMIN_ID=? ";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1,admin.getId());
			ps2.executeUpdate();
			
			//添加此ADMIN对象对应的新角色信息;
			String sql3 = "insert into ADMIN_ROLE(ADMIN_ID,ROLE_ID) values(?,?)";
			PreparedStatement ps3 = con.prepareStatement(sql3);
			String[] roleIds = admin.getRoleIds();
			if(roleIds!=null&&roleIds.length>0){
				for(int i=0;i<roleIds.length;i++){
					ps3.setObject(1,admin.getId());
					ps3.setObject(2,Integer.valueOf(roleIds[i]));
					ps3.addBatch();
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
			throw new DAOException("更新管理员信息错误！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据ID删除管理员信息;
	public void deleteById(Integer id) throws DAOException {
		if(id==null){
			return;
		}
		try {
			Connection con = DBUtil.getConnection();
			con.setAutoCommit(false);
			//根据ID删除ADMIN_INFO表中的相关管理员信息;
			String sql = "delete from ADMIN_INFO where ID=? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeUpdate();
			
			//根据ID删除ADMIN_ROLE表中相关的角色信息;
			String sql2 = "delete from ADMIN_ROLE where ADMIN_ID=? ";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setInt(1,id);
			ps2.executeUpdate();
			
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DBUtil.getConnection().rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DAOException("删除管理员信息错误！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		IAdminDao dao = new AdminDaoImpl();
//		List<Integer> roleIds = dao.findRoleIdByPrivilegeIdAndRoleName("-1","");
//		System.out.println(roleIds.size());
//		for(Integer t:roleIds){
//		System.out.println(t);
//		}
//		int totalPages = dao.findTotalPages(roleIds,3);
//		System.out.println(totalPages);
		
		List<Admin> admins = dao.findByCondition(null,null,1,3);
		System.out.println(admins.size());
	}
	
}
