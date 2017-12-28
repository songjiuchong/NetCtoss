package com.netctoss.dao.cost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.netctoss.dao.DAOException;
import com.netctoss.pojo.Cost;
import com.netctoss.util.DBUtil;

public class CostDaoImpl implements ICostDao{
	
	//查询所有Cost记录并返回一个cost对象列表(每行记录代表一个cost对象);
	private String findAllSql = "select * from COST";
	
	public List<Cost> findAll() throws DAOException {
		 List<Cost> list = null;
		 try{
			Connection con = DBUtil.getConnection();
		 	PreparedStatement ps = con.prepareStatement(findAllSql);
		 	ResultSet rs = ps.executeQuery();
		 	while(rs.next()){
		 		Cost c = createCost(rs);
		 		if(list==null)
		 		list = new ArrayList<Cost>();
		 		list.add(c);
		 	}
		 return list;
		 }catch(SQLException e){
		 	e.printStackTrace();
		 	throw new DAOException("查询全部的资费数据时发生错误!",e);
		 }finally{
		 	DBUtil.closeConnection();
		 }
	}
	
	//分页查找记录;
	private String findByPageSql="select * from (select x.*,rownum r from("+
	"select * from COST order by ID) x "+
	"where rownum<?) where r>?";
	
	public List<Cost> findByPage(int page, int pageSize) throws DAOException {
		 List<Cost> list = null;
		 try{
			Connection con = DBUtil.getConnection();
		 	PreparedStatement ps = con.prepareStatement(findByPageSql);
		 	
		 	//下一页的最小行;
		 	int nextMin = page*pageSize + 1;
		 	//上一页的最大行;
		 	int lastMax = (page-1)*pageSize;
		 	
		 	ps.setInt(1,nextMin);
		 	ps.setInt(2,lastMax);
		 	ResultSet rs = ps.executeQuery();
		 	while(rs.next()){
		 		Cost c = createCost(rs);
		 		if(list==null)
		 		list = new ArrayList<Cost>();
		 		list.add(c);
		 	}
		 return list;
		 }catch(SQLException e){
		 	e.printStackTrace();
		 	throw new DAOException("分页查询数据时发生错误!",e);
		 }finally{
		 	DBUtil.closeConnection();
		 }
	}	

	//查询总页数;
	private String findTotalRowsSql = "select count(*) from COST";
	
	public int findTotalPage(int pageSize) throws DAOException {
		 try{
			Connection con = DBUtil.getConnection();
		 	PreparedStatement ps = con.prepareStatement(findTotalRowsSql);
		 	ResultSet rs = ps.executeQuery();
		 	rs.next();
		 	int rows = rs.getInt(1); //得到总行数;
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
	
	
	//根据一条查询结果创建一个cost对象;
	private Cost createCost(ResultSet rs) throws SQLException{
		Cost c = new Cost();
		c.setId(rs.getInt("ID"));
		c.setName(rs.getString("NAME"));
		c.setBaseDuration(rs.getInt("BASE_DURATION"));
		c.setBaseCost(rs.getDouble("BASE_COST"));
		c.setUnitCost(rs.getDouble("UNIT_COST"));
		c.setStatus(rs.getString("STATUS"));
		c.setDescr(rs.getString("DESCR"));
		c.setCreateTime(rs.getDate("CREATIME"));
		c.setStartTime(rs.getDate("STARTIME"));
		c.setCostType(rs.getString("COST_TYPE"));
		return c;
	}


	//删除资费数据(由于SERVICE表的COST_ID对此表的ID有外键限制,所以在删除时可能会出现"ORA-02292: integrity constraint (SYSTEM.SERVICE_COST_ID_FK) violated");
	private String deleteByIdSql="delete from COST where ID=?";
	
	public void deleteById(int id) throws DAOException {
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(deleteByIdSql);
			ps.setInt(1,id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("删除资费数据失败",e);
		} finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据name进行查询的SQL;
	private String findByNameSql="select * from COST where NAME=?";
	
	public Cost findByName(String name) throws DAOException {
		
		if(name == null)
			return null;
		
		 try{
			Connection con = DBUtil.getConnection();
		 	PreparedStatement ps = con.prepareStatement(findByNameSql);
		 	ps.setString(1,name);
		 	ResultSet rs = ps.executeQuery();
		 	while(rs.next()){
		 		Cost c = createCost(rs);
		 		return c;
		 	}
		 	return null;
		 }catch(SQLException e){
		 	e.printStackTrace();
		 	throw new DAOException("根据名称查询资费数据时发生异常!",e);
		 }finally{
		 	DBUtil.closeConnection();
		 }
	}
	
	//根据Cost对象为数据库添加一条资费数据;
	private String addCostSql = 
			"insert into "+
			"COST(ID,NAME,BASE_DURATION,BASE_COST,UNIT_COST,STATUS,DESCR,CREATIME,COST_TYPE) "+
			"values(cost_seq.nextval,?,?,?,?,'1',?,sysdate,?)"; //参考cost_seq.sql;
	
	public void addCost(Cost cost) throws DAOException {
		if(cost==null){
			return;
		}
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(addCostSql);
			ps.setString(1,cost.getName());
			ps.setObject(2,cost.getBaseDuration());
			ps.setObject(3,cost.getBaseCost());
			ps.setObject(4,cost.getUnitCost());
			ps.setString(5,cost.getDescr());
			ps.setString(6,cost.getCostType());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("新增资费数据失败",e);
		} finally{
			DBUtil.closeConnection();
		}
	}
	
	//根据id进行查询的SQL;
	private String findByIdSql = "select * from COST where ID=?";
	
	public Cost findById(Integer id) throws DAOException {
		
		if(id == null)
			return null;
		
		 try{
			Connection con = DBUtil.getConnection();
		 	PreparedStatement ps = con.prepareStatement(findByIdSql);
		 	ps.setInt(1,id);
		 	ResultSet rs = ps.executeQuery();
		 	while(rs.next()){
		 		Cost c = createCost(rs);
		 		return c;
		 	}
		 	return null;
		 }catch(SQLException e){
		 	e.printStackTrace();
		 	throw new DAOException("根据ID查询资费数据时发生异常!",e);
		 }finally{
		 	DBUtil.closeConnection();
		 }
	}
	
	//根据cost对象将其中属性更新到数据库;
	public void updateCost(Cost cost) throws DAOException {

		String updateCostSql =
			"update COST set Name=?,BASE_DURATION=?,BASE_COST=?,UNIT_COST=?,DESCR=?,COST_TYPE=? where ID=?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement ps = con.prepareStatement(updateCostSql);
			ps.setString(1,cost.getName());
			ps.setObject(2,cost.getBaseDuration());
			ps.setObject(3,cost.getBaseDuration());
			ps.setObject(4,cost.getUnitCost());
			ps.setString(5,cost.getDescr());
			ps.setString(6,cost.getCostType());
			ps.setObject(7,cost.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("更新资费数据失败",e);
		} finally{
			DBUtil.closeConnection();
		}
	}		
	
	//根据name进行查询的SQL,排除了当前id行;
	public Cost findByNameForUpdate(String name, Integer id)
			throws DAOException {
		if(name == null||id==null)
			return null;
		String findByNameForUpdateSql=
			"select * from COST where NAME=? and ID!=?";
		 try{
			Connection con = DBUtil.getConnection();
		 	PreparedStatement ps = con.prepareStatement(findByNameForUpdateSql);
		 	ps.setString(1,name);
		 	ps.setInt(2,id);
		 	ResultSet rs = ps.executeQuery();
		 	while(rs.next()){
		 		Cost c = createCost(rs);
		 		return c;
		 	}
		 	return null;
		 }catch(SQLException e){
		 	e.printStackTrace();
		 	throw new DAOException("根据名称以及ID查询资费数据时发生异常!",e);
		 }finally{
		 	DBUtil.closeConnection();
		 }
	}

	
	//单元测试;
	public static void main(String[] args)throws Exception{
		ICostDao dao = new CostDaoImpl();
		dao.updateCost(null);
//		for(Cost c:costs){
//			System.out.println("id:"+c.getId()+" name:"+c.getName());
//		}
	}

}

