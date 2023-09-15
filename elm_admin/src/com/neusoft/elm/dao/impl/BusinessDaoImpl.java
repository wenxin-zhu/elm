package com.neusoft.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.util.DBUtil;

public class BusinessDaoImpl implements BusinessDao {

	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	// 搜索商家
	@Override
	public List<Business> listBusiness(String businessName,String businessAddress) {

		List<Business> list = new ArrayList<>();
        // sql语句，修改较多的字符串用StringBuffer
		StringBuffer sql = new StringBuffer("select * from business where 1=1 ");
		// 不为null且不为空字符串
		if(businessName!=null&&!businessName.equals("")) {
			// like 模糊查询
			sql.append(" and businessName like '%" + businessName+"%' ");
		}
		if(businessAddress!=null&&!businessAddress.equals("")) {
			sql.append(" and businessAddress like '%" + businessAddress+"%' ");
		}
		try {
			// 建立连接并执行sql语句
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				Business business = new Business();
//	@Override
	
	
//	public List<Business> listBusiness() {
//			List<Business> list = new ArrayList();
//			String sql = "select * from business order by businessId";
//		
//			try {
//				con = DBUtil.getConnection();
//				pst = con.prepareStatement(sql);
//				rs = pst.executeQuery();
//				while(rs.next()) {
//					Business business = new Business();
					business.setBusinessId(rs.getInt("businessId"));
					business.setPassword(rs.getString("password"));
					business.setBusinessName(rs.getString("businessName"));
					business.setBusinessAddress(rs.getString("businessAddress"));
					business.setBusinessExplain(rs.getString("businessExplain"));
					business.setStarPrice(rs.getDouble("StarPrice"));
					business.setDeliveryPrice(rs.getDouble("DeliveryPrice"));
					list.add(business);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBUtil.close(rs, pst, con);
			}
		return list;
	}

	@Override
	public Business getBusinessByIdByPass(Integer businessId, String password) {
		Business business = null;
		String sql = "select * from business where businessId=? and password=?";
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, businessId);
			pst.setString(2, password);
			rs = pst.executeQuery();
			//实际上只读取了一个商家账户
			while(rs.next()) {
				business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
//				list.add(business);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
//		return list;
		return business;
	}
	
	// 添加商家
	@Override
	public int saveBusiness(String businessName) {
	int businessId = 0;
	String sql = "insert into business(businessName,password) values(?,'123')";
	try {
		con = DBUtil.getConnection();
		//设置返回自增长列值
		pst = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		pst.setString(1, businessName);
		pst.executeUpdate();
		//获取自增长列值（一行一列）
		rs = pst.getGeneratedKeys();
		if(rs.next()) {
			businessId = rs.getInt(1);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		return businessId;
	}
	
	@Override
	public int removeBusiness(int businessId) {
		int result = 0;
		// 由于外键约束，要先删除Food表中的数据
		String delFootSql = "delete from food where businessId=?";
		String delBusinessSql = "delete from business where businessId=?";
		try {
			con = DBUtil.getConnection();
			//开启一个事务（关闭自动提交），其中的操作要么都成功，要么都失败
			con.setAutoCommit(false);
			// 删除Food表中的对应数据
			pst = con.prepareStatement(delFootSql);
			pst.setInt(1, businessId);
			pst.executeUpdate();
			// 删除Business表中的对应数据
			pst = con.prepareStatement(delBusinessSql);
			pst.setInt(1, businessId);
			result = pst.executeUpdate();

			// 手动提交
			con.commit();
		} catch (SQLException e) {
			result = 0;
			// 如果没有全部成功就回滚
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close(null, pst, con);
		}
	return result;
	}
	
	@Override
	public Business getBusinessById(Integer businessId) {
		Business business = null;
		String sql = "select * from business where businessId=?";
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, businessId);
			rs = pst.executeQuery();
		//实际上只查询了一个商家信息	
		while(rs.next()) {
			business = new Business();
			business.setBusinessId(rs.getInt("businessId"));
			business.setPassword(rs.getString("password"));
			business.setBusinessName(rs.getString("businessName"));
			business.setBusinessAddress(rs.getString("businessAddress"));
			business.setBusinessExplain(rs.getString("businessExplain"));
			business.setStarPrice(rs.getDouble("starPrice"));
			business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(rs, pst, con);
		}
		
		return business;
	}

	@Override
	public int updateBusiness(Business business) {
		int result = 0;
		String sql = "update business set businessName=?,businessAddress=?,businessExplain=?,starPrice=?,deliveryPrice=? where businessId=?";
		
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, business.getBusinessName());
			pst.setString(2, business.getBusinessAddress());
			pst.setString(3, business.getBusinessExplain());
			pst.setDouble(4, business.getStarPrice());
			pst.setDouble(5, business.getDeliveryPrice());
			pst.setInt(6, business.getBusinessId());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.close(null, pst, con);
		}
		
		return result;
	}

	@Override
	public int updateBusinessByPassword(Integer businessId, String password) {
		int result = 0;
		String sql = "update business set password=? where businessId=?";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, password);
			pst.setInt(2, businessId);
			result = pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(null, pst, con);
		}
		
		return result;
	}
	
	}

