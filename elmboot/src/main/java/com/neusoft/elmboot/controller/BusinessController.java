package com.neusoft.elmboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.elmboot.po.Business;
import com.neusoft.elmboot.service.BusinessService;

@RestController
@RequestMapping("/BusinessController")
public class BusinessController {

	@Autowired
	private BusinessService businessService;

	// 根据点餐分类编号查询所属商家信息
	@RequestMapping("/listBusinessByOrderTypeId")
	public List<Business> listBusinessByOrderTypeId(Business business) throws Exception {
		return businessService.listBusinessByOrderTypeId(business.getOrderTypeId());
	}

	// 根据商家编号查询商家信息
	@RequestMapping("/getBusinessById")
	public Business getBusinessById(Business business) throws Exception {
		return businessService.getBusinessById(business.getBusinessId());
	}
}
