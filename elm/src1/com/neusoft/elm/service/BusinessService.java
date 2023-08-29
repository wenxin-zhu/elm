package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessService {

	// 根据点餐分类编号查询所属商家信息
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
	// 根据商家编号查询商家信息
	public Business getBusinessById(Integer businessId);
}
