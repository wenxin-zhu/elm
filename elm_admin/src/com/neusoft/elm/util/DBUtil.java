package com.neusoft.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DBUtil {
	//jdbc:mysql是jdbc连接mysql数据库的协议
	private static final String URL ="jdbc:mysql://localhost:3306/elm_admin?characterEncoding=utf-8";
	private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
	private static final String USERNAME ="root";
	private static final String PASSWORD ="123456";
	
	//获取Connection
	public static Connection getConnection() {
		Connection con = null;
		try {
			//Class.forName()通过提供限定类名作为参数，并返回相应的对象，加载数据库连接的驱动程序
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	//关闭资源,静态方法不用实例化可以直接调用
	public static void closeConnection(ResultSet rs,PreparedStatement pst,Connection con) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rs = null;  //释放资源
		}
		if(pst!=null) {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pst = null;  //释放资源
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = null;  //释放资源
		}
	}
}
