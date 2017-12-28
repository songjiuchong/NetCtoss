import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {

	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		try {
			Properties p = new Properties();
			p.load(DBUtil.class.getClassLoader().getResourceAsStream(
					"db.properties"));
			ds.setJdbcUrl(p.getProperty("url"));
			ds.setDriverClass(p.getProperty("driverClassName"));
			ds.setUser(p.getProperty("username"));
			ds.setPassword(p.getProperty("password"));
			ds.setInitialPoolSize(Integer.valueOf(p.getProperty("initSize")));
			ds.setMaxPoolSize(Integer.valueOf(p.getProperty("maxSize")));
			ds.setMinPoolSize(Integer.valueOf(p.getProperty("minSize")));
			ds.setAcquireIncrement(Integer.valueOf(p.getProperty("increment")));
			ds.setMaxIdleTime(Integer.valueOf(p.getProperty("maxIdleTime")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection con = tl.get();
		if (con == null) {
			try {
				con = ds.getConnection();
				tl.set(con);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}

	public static void close() {
		Connection con = tl.get();
		if (con != null) {
			try {
				con.close();
				tl.set(null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Connection con = getConnection();
		System.out.println(con.getClass().getName());
	}

}
