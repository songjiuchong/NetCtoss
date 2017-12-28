package com.netctoss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
		private static String user;
		private static String password;
		private static String url;
		private static String driverName;
		private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
		
		static{
			Properties p = new Properties();
			try{
			//通过加载DBUtil的类加载器和它加载此类的流读取db.properties文件,注意参数地址是从src中开始的;
			p.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			user = p.getProperty("user");
			password = p.getProperty("password");
			url = p.getProperty("url");
			driverName = p.getProperty("driverName");
			Class.forName(driverName);
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException("读取数据库配置文件失败");
			}
		}
			
			//获取数据库连接,返回一个连接;
			public static Connection getConnection() throws SQLException{
				Connection con = tl.get();
				if(con == null){
					try{
					con = DriverManager.getConnection(url,user,password);
					tl.set(con);
					}catch(SQLException e){
						e.printStackTrace();
						throw new SQLException("创建数据库连接失败");
					}
				}
				return tl.get();
			}
			
			public static void closeConnection(){
				Connection con = tl.get();
				if(con !=null){
					try{
					con.close();
					tl.set(null);
					}catch(SQLException e){
						e.printStackTrace();
						throw new RuntimeException("关闭数据库连接失败");
					}
				}
			}
	
	//测试是否能获得一个连接;
	public static void main(String[] args) throws SQLException {
		System.out.println(getConnection());
	}

}
