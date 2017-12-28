package com.netctoss.dao.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.dao.DAOFactory;
import com.netctoss.pojo.Service;
import com.netctoss.util.DBUtil;
import com.netctoss.vo.ServiceVo;

public class ServiceDaoImpl implements IServiceDao{
	
	//根据所给查询条件查询业务帐号视图对象列表;
	public List<ServiceVo> findByCondition(String osUserName, String unixHost,
			String idCardNo, String status, int page, int pageSize)
			throws DAOException {
	
	List<Object> params = new ArrayList<Object>();
	String sql = createFindSQL(osUserName,unixHost,idCardNo,status,params,page,pageSize);
	List<ServiceVo> list = null;
	 try{
		Connection con = DBUtil.getConnection();
	 	PreparedStatement ps = con.prepareStatement(sql);
	 	for(int i=0;i<params.size();i++){
	 	ps.setObject(i+1, params.get(i));
	 	}
	 	ResultSet rs = ps.executeQuery();
	 	while(rs.next()){
	 		ServiceVo s = createServiceVo(rs);
	 		if(list==null)
	 		list = new ArrayList<ServiceVo>();
	 		list.add(s);
	 	}
	 return list;
	 }catch(SQLException e){
	 	e.printStackTrace();
	 	throw new DAOException("查询业务帐号视图数据时发生错误!",e);
	 }finally{
	 	DBUtil.closeConnection();
	 }
}
	
	//根据所给的查询条件构造业务账号视图SQL查询语句;
	/*
	select * from(
	select s.*,a.idcard_no,a.real_name,c.name,c.descr,rownum n from service s
	inner join account a
	on s.account_id=a.id 
	inner join cost c
	on s.cost_id=c.id
	where rownum<?)
	where r>?
	*/
	
	private String createFindSQL(String osUserName, String unixHost,
			String idCardNo, String status,List<Object> params, int page, int pageSize){
		
		StringBuffer sb = new StringBuffer();
		sb.append("select x.*,rownum n from(select s.*,a.idcard_no,a.real_name,c.name,c.descr from service s ");
		sb.append("inner join account a on s.account_id=a.id ");
		sb.append("inner join cost c on s.cost_id=c.id order by s.ID) x ");
		
		sb.append("where 1=1 ");
		if(osUserName!=null && osUserName.length()>0){
			sb.append("and x.OS_USERNAME=? ");
			params.add(osUserName);
		}
		if(unixHost!=null && unixHost.length()>0){
			sb.append("and x.UNIX_HOST=? ");
			params.add(unixHost);
		}
		if(idCardNo!=null && idCardNo.length()>0){
			sb.append("and x.IDCARD_NO=? ");
			params.add(idCardNo);
		}
		if(status!=null && !status.equals("-1")){
			sb.append("and x.STATUS=? ");
			params.add(status);
		}
		
		sb.append("and rownum<? ");
		
		//小于下一页的最小行;
		int nextMin = page*pageSize+1;
		params.add(nextMin);
		
		String sql = "select * from ("
			+sb.toString()
			+") where n>? ";
		
		//大于上一页的最大行;
		int lastMax = (page-1)*pageSize;
		params.add(lastMax);
		
		return sql;
	}
	
	//根据业务帐号ID生成一个业务帐号视图对象;
	public ServiceVo findByServiceId(Integer serviceId) throws DAOException {
		
		String sql = "select s.*,a.idcard_no,a.real_name,c.name,c.descr from SERVICE s "+
					 "inner join ACCOUNT a on s.account_id=a.id "+
					 "inner join COST c on s.cost_id=c.id "+
					 "where s.ID=?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1,serviceId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			ServiceVo sv = createServiceVo(rs);
			System.out.println(sv.getServiceId());
			return sv;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("根据业务帐号ID生成一个业务帐号视图对象发生错误！",e);
		}
			return null;
	}
	
	//根据一条查询结果创建一个serviceVo对象;
	private ServiceVo createServiceVo(ResultSet rs) throws SQLException{
		ServiceVo s = new ServiceVo();
		s.setServiceId(rs.getInt("ID"));
		s.setAccountId(rs.getInt("ACCOUNT_ID"));
		s.setUnixHost(rs.getString("UNIX_HOST"));
		s.setOsUsername(rs.getString("OS_USERNAME"));
		s.setLoginPasswd(rs.getString("LOGIN_PASSWD"));
		s.setStatus(rs.getString("STATUS"));
		s.setCreateDate(rs.getDate("CREATE_DATE"));
		s.setPauseDate(rs.getDate("PAUSE_DATE"));
		s.setCloseDate(rs.getDate("CLOSE_DATE"));
		s.setCostId(rs.getInt("COST_ID"));
		s.setIdcardNo(rs.getString("IDCARD_NO"));
		s.setRealName(rs.getString("REAL_NAME"));
		s.setCostName(rs.getString("NAME"));
		s.setDescr(rs.getString("DESCR"));
		return s;
	}
	
	//根据一条查询结果创建一个service对象;
	private Service createService(ResultSet rs) throws SQLException{
		Service s = new Service();
		s.setServiceId(rs.getInt("ID"));
		s.setAccountId(rs.getInt("ACCOUNT_ID"));
		s.setUnixHost(rs.getString("UNIX_HOST"));
		s.setOsUsername(rs.getString("OS_USERNAME"));
		s.setLoginPasswd(rs.getString("LOGIN_PASSWD"));
		s.setStatus(rs.getString("STATUS"));
		s.setCreateDate(rs.getDate("CREATE_DATE"));
		s.setPauseDate(rs.getDate("PAUSE_DATE"));
		s.setCloseDate(rs.getDate("CLOSE_DATE"));
		s.setCostId(rs.getInt("COST_ID"));
		return s;
	}
	
	//查询总页数;
	public int findTotalPage(String osUserName, String unixHost,
			String idCardNo, String status,int pageSize) throws DAOException {
		//构造查询的SQL;
		List<Object> params = new ArrayList<Object>();
		String sql = createFindTotalPageSQL(osUserName,unixHost,idCardNo,status,params);
		try{
				Connection con = DBUtil.getConnection();
			 	PreparedStatement ps = con.prepareStatement(sql);
			 	for(int i=0;i<params.size();i++){
			 		ps.setObject(i+1,params.get(i));
			 	}
			 	ResultSet rs = ps.executeQuery();
			 	rs.next();
			 	int rows = rs.getInt(1); //取得这一条结果集中第一列的数据,得到总行数;
			 	if(rows%pageSize==0){
			 		//如果整除,则返回相除的值;
			 		return rows/pageSize;
			 	}else{
			 		//有余数的情况下,进位取整;
			 		return rows/pageSize+1;
			 	}
			 }catch(SQLException e){
			 	e.printStackTrace();
			 	throw new DAOException("查询总页数时发生错误!",e);
			 }finally{
			 	DBUtil.closeConnection();
			 }	
	}
		
	//根据所给的查询条件构造查找总页数的SQL查询语句;
	private String createFindTotalPageSQL(String osUserName, String unixHost,
			String idCardNo, String status,List<Object> params){
			
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) from service s ");
		sb.append("inner join account a on s.account_id=a.id ");
		sb.append("inner join cost c on s.cost_id=c.id ");
		sb.append("where 1=1 ");
		if(osUserName!=null && osUserName.length()>0){
			sb.append("and s.OS_USERNAME=? ");
			params.add(osUserName);
		}
		if(unixHost!=null && unixHost.length()>0){
			sb.append("and UNIX_HOST=? ");
			params.add(unixHost);
		}
		if(idCardNo!=null && idCardNo.length()>0){
			sb.append("and a.IDCARD_NO=? ");
			params.add(idCardNo);
		}
		if(status!=null && !status.equals("-1")){
			sb.append("and s.STATUS=? ");
			params.add(status);
		}
		return sb.toString();
	}
	
	//根据Service对象为数据库添加一条业务账号数据;
	public void addService(Service service) throws DAOException {
		String sql = "insert into "+
				     "SERVICE(ID,ACCOUNT_ID,COST_ID,UNIX_HOST,OS_USERNAME,LOGIN_PASSWD,CREATE_DATE,STATUS) "+ 
				     "values(account_seq.nextval,?,?,?,?,?,sysdate,'0')"; //参考service_seq.sql;
		
		if(service == null){
			return;
		}
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setObject(1,service.getAccountId());
			ps.setObject(2,service.getCostId());
			ps.setObject(3,service.getUnixHost());
			ps.setObject(4,service.getOsUsername());
			ps.setObject(5,service.getLoginPasswd());
			ps.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("新增业务账号操作失败！",e);
		}finally{
			DBUtil.closeConnection();
		}
	}

	//根据id更新开通状态;
	public void startService(int id) throws DAOException {
		String sql="update SERVICE set STATUS=0,PAUSE_DATE=null where ID=?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("更新业务账号状态为开通失败",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据id更新暂停状态;
	public void pauseService(int[] id) throws DAOException {
		if(id == null|| id.length == 0){
			return;
		}
		String sql="update SERVICE set STATUS=1,PAUSE_DATE=SYSDATE where ID in (";
		for(int i=0;i<id.length;i++){
			sql += (i==0)? "?":",?";
		}
		sql+=")";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			for(int i=0;i<id.length;i++){
				ps.setInt(i+1,id[i]);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("更新业务账号状态为暂停失败",e);
		}finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据id更新删除状态;
	public void deleteService(int[] id) throws DAOException {
		if(id==null || id.length==0){
			return;
		}
		String sql = "update SERVICE set STATUS=2,CLOSE_DATE=SYSDATE where ID in(";
		for(int i=0;i<id.length;i++){
			sql+= (i==0)? "?":",?";
		}
		sql+=")";
			try {
				Connection con = DBUtil.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				for(int i=0;i<id.length;i++){
					ps.setInt(i+1,id[i]);
				}
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException("更新业务账号状态为删除失败",e);
			}finally{
				DBUtil.closeConnection();
			}	
	}
	
	//根据id查询一条业务账号数据;
	private String findByIdSql = "select * from SERVICE where ID=?";
	public Service findById(Integer id) throws DAOException {
		if(id == null)
			return null;
		 try{
			Connection con = DBUtil.getConnection();
		 	PreparedStatement ps = con.prepareStatement(findByIdSql);
		 	ps.setInt(1,id);
		 	ResultSet rs = ps.executeQuery();
		 	while(rs.next()){
		 		Service s = createService(rs);
		 		return s;
		 	}
		 	return null;
		 }catch(SQLException e){
		 	e.printStackTrace();
		 	throw new DAOException("根据ID查询业务账号数据时发生异常!",e);
		 }finally{
		 	DBUtil.closeConnection();
		 }
	}
	
	//根据传入参数进行更新Service记录;
	private String updateServiceSql =
			"update SERVICE set COST_ID=? where ID=?"; 
	public void updateService(Service service) throws DAOException {
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(updateServiceSql);
			ps.setObject(1,service.getCostId());
			ps.setObject(2,service.getServiceId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("更新业务账号信息失败！",e);
		} finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据账务账号ID查询所有业务账号;
	public List<Service> findServiceByAccountId(int accountId)
			throws DAOException {
		String sql = "select * from SERVICE where ACCOUNT_ID=?";
		List<Service> list = null;
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,accountId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Service s = createService(rs);
				if(list == null){
					list = new ArrayList<Service>();
				}
				list.add(s);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("根据账务账号ID查询业务账号数据失败！",e);
		} finally{
			DBUtil.closeConnection();
		}
	}
	
	
	public static void main(String[] args) throws DAOException{
		IServiceDao dao = DAOFactory.getIServiceDao();
		List<ServiceVo> s = dao.findByCondition(null, null, null, "-1", 1, 5);
		System.out.println(s.size());
		for(ServiceVo t:s){
			System.out.println(t.getServiceId());
		}
	}

}
