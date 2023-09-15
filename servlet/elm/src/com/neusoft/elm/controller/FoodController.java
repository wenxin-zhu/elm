package com.neusoft.elm.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.neusoft.elm.po.Food;
import com.neusoft.elm.service.FoodService;
import com.neusoft.elm.service.impl.FoodServiceImpl;

public class FoodController {
	
	// 根据商家编号查询所属食品信息
	public Object listFoodByBusinessId(HttpServletRequest request) throws Exception{
		Integer businessId = Integer.valueOf(request.getParameter("businessId"));
		FoodService service = new FoodServiceImpl();
		List<Food> list = service.listFoodByBusinessId(businessId);
		return list;
	}

}