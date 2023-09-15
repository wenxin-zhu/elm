package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {

	// 根据点餐分类编号查询所属商家信息
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws Exception;
	
	// 根据商家编号查询商家信息
	public Business getBusinessById(Integer businessId) throws Exception;
}
