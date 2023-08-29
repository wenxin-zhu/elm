package com.neusoft.elm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 数据库工具类
public class DBUtil {
	
	// 数据库相关配置
	private static final String URL = "jdbc:mysql://localhost:3306/elm?characterEncoding=utf-8";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	
	// 线程局部对象
	private static final ThreadLocal<Connection> TL = new ThreadLocal<>();
	
	// 创建连接
	private static Connection createConnection() {
		 Connection con = null;
		 if (con == null) {
			 try {
				 Class.forName(DRIVER);
				 con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 }
		 return con;
	}
	
	// 获取连接
	public static Connection getConnection() {
		Connection con = null;
		// 从线程局部对象中获取Connection
		con = TL.get();
		if (con==null) {
			con = createConnection();
			TL.set(con);
		}
		return con;
	}
	
	
	
	// 开启一个事务
	public static void beginTransaction() throws Exception {
		Connection con = null;
		con = TL.get();
		if (con == null) {
			con = createConnection();
			TL.set(con);
		}
		con.setAutoCommit(false);
	}
	
	// 提交一个事务
	public static void commitTransaction() throws Exception {
		Connection con = TL.get();
		con.commit();
	}
	
	// 回滚一个事务
	public static void rollbackTransaction() throws Exception {
		Connection con = TL.get();
		con.rollback();
	}
	
	// 关闭各种资源（dao层查询）
	public static void close(ResultSet rs, PreparedStatement pst) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 关闭各种资源（dao层增删改）
	public static void close(PreparedStatement pst) {
		try {
			if (pst != null) {
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 关闭各种资源
	public static void close() {
		Connection con = TL.get();
		try {
			if (con != null) {
			con.close();
		}
		//至关重要，否则容易造成内存溢出等问题。
		TL.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
