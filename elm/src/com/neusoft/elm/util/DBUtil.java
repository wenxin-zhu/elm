package com.neusoft.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DBUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/elm_admin?characterEncoding=utf-8";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	
	private static final ThreadLocal<Connection> TL = new ThreadLocal<>();
	//获取Connection,Dao取得出来就取，取不出看来就创建再放进去
	public static Connection getConnection() {
		Connection con = null;
		con =TL.get();
		if(con == null) {
			con = createConnection();
			TL.set(con);
		}
		return con;
	}
	
	//开启一个事物
	public static void beginTransaction() throws Exception{
		Connection con = null;
		con = TL.get();
		if(con == null) {
			con = createConnection();
			TL.set(con);
		}
		//开启事务
		con.setAutoCommit(false);
	}
	
	//提交一个事务
	public static void commitTransaction() throws Exception{
		Connection con = TL.get();
		con.commit();
	}
	
	//回滚一个事务
	public static void rollbackTransaction() throws Exception{
		Connection con = TL.get();
		con.rollback();
	}
	
	private static Connection createConnection() {
		Connection con = null;
		if(con==null) {
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return con;
	}
	
	//关闭资源
	private static void close(ResultSet rs, PreparedStatement pst) {
			try {
				if(rs!=null) {
				rs.close();
				}
				if(pst!=null) {
					pst.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private static void close(PreparedStatement pst) {
		try {
			if(pst!=null) {
				pst.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void close() {
		Connection con = TL.get();
		try {
			if(con!=null) {
				con.close();
			}
			//必须要加上remove防止内存泄露
			TL.remove();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
