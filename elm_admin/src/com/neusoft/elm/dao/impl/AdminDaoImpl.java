package com.neusoft.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class AdminDaoImpl implements AdminDao{
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	@Override
	public Admin getAdminByNameByPass(String adminName, String password) {
		Admin admin = null;
		// 查询语句
		String sql = "select * from admin where adminName=? and password=?";
		try {
			// 获取数据库连接
			con = DBUtil.getConnection();
			// 预编译
			pst = con.prepareStatement(sql);
			// 设置sql语句中的两个参数
			pst.setString(1, adminName);
			pst.setString(2, password);
			// 执行query，返回ResultSet
			rs = pst.executeQuery();
			while(rs.next()) {
				// 创建admin对象
				admin = new Admin();
				admin.setAdminId(rs.getInt("adminId"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DBUtil.close(rs, pst, con);
		}
		// 若获取失败返回null
		return admin;
	}
}
