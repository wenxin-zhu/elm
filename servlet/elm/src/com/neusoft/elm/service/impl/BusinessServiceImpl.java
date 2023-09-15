package com.neusoft.elm.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.util.DBUtil;

public class BusinessServiceImpl implements BusinessService{
	
	// 根据点餐分类编号查询所属商家信息
	@Override
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) {
		List<Business> list = new ArrayList<>();
		BusinessDao dao = new BusinessDaoImpl();
		try {
			DBUtil.getConnection();
			list = dao.listBusinessByOrderTypeId(orderTypeId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
		return list;
	}
	 
	// 根据商家编号查询商家信息
	@Override
	public Business getBusinessById(Integer businessId) {
		Business business = null;
		BusinessDao dao = new BusinessDaoImpl();
		try {
			DBUtil.getConnection();
			business = dao.getBusinessById(businessId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
		return business;
	}
}
