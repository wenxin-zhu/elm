package com.neusoft.elm.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.service.impl.BusinessServiceImpl;

public class BusinessController {
	
	// 根据点餐分类编号查询所属商家信息
	public Object listBusinessByOrderTypeId(HttpServletRequest request) throws Exception{
		Integer orderTypeId = Integer.valueOf(request.getParameter("orderTypeId"));
		BusinessService service = new BusinessServiceImpl();
		List<Business> list= service.listBusinessByOrderTypeId(orderTypeId);
		return list;
	}
	
	// 根据商家编号查询商家信息
	public Object getBusinessById(HttpServletRequest request) throws Exception{
		Integer businessId = Integer.valueOf(request.getParameter("businessId"));
		BusinessService service = new BusinessServiceImpl();
		Business business = service.getBusinessById(businessId);
		return business;
	}
}