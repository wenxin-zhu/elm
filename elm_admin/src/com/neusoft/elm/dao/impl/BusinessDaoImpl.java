package com.neusoft.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.util.DBUtil;

public class BusinessDaoImpl implements BusinessDao {

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	@Override
//	public List<Business> listBusiness(String businessName,String businessAddress) {
	public List<Business> listBusiness() {
		List<Business> list = new ArrayList<>();
        // sql语句		
//		StringBuffer sql = new StringBuffer("select * from business where 1=1 ");
//		if(businessName!=null&&!businessName.equals("")) {
//			sql.append(" and businessName like '%" + businessName+"%' ");
//		}
//		if(businessAddress!=null&&!businessAddress.equals("")) {
//			sql.append(" and businessAddress like '%" + businessAddress+"%' ");
//		}
		String sql = "select * from business order by businessId";
		try {
			con = DBUtil.getConnection();
			pst = con.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()) {
				Business business = new Business();
				business.setBusinessId(rs.getInt("businessId"));
				business.setPassword(rs.getString("password"));
				business.setBusinessName(rs.getString("businessName"));
				business.setBusinessAddress(rs.getString("businessAddress"));
				business.setBusinessExplain(rs.getString("businessExplain"));
				business.setStarPrice(rs.getDouble("starPrice"));
				business.setDeliveryPrice(rs.getDouble("deliveryPrice"));
				list.add(business);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pst, con);
		}
		return list;
	}
}
