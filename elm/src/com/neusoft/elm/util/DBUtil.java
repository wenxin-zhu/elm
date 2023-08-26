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
	//��ȡConnection,Daoȡ�ó�����ȡ��ȡ���������ʹ����ٷŽ�ȥ
	public static Connection getConnection() {
		Connection con = null;
		con =TL.get();
		if(con == null) {
			con = createConnection();
			TL.set(con);
		}
		return con;
	}
	
	//����һ������
	public static void beginTransaction() throws Exception{
		Connection con = null;
		con = TL.get();
		if(con == null) {
			con = createConnection();
			TL.set(con);
		}
		//��������
		con.setAutoCommit(false);
	}
	
	//�ύһ������
	public static void commitTransaction() throws Exception{
		Connection con = TL.get();
		con.commit();
	}
	
	//�ع�һ������
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
	
	//�ر���Դ
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
			//����Ҫ����remove��ֹ�ڴ�й¶
			TL.remove();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
