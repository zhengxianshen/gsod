package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author zhengxs
 * @Date 19-6-24 下午5:19
 * @Since 1.0
 */
public class DB {
	private static Connection conn = null;
	
	private static final String url = "jdbc:mysql://localhost:3306/gsod?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	private static final String user = "root";
	private static final String password = "root";
	
	public static Connection getConn(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			if(conn==null)System.out.println("nulllllll");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(conn==null)return null;
		return conn;
	}
	
}
