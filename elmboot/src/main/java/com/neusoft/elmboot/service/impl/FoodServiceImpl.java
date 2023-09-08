package com.neusoft.elmboot.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neusoft.elmboot.mapper.FoodMapper;
import com.neusoft.elmboot.po.Food;
import com.neusoft.elmboot.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodMapper foodMapper;

	// 根据商家编号查询所属食品信息
	@Override
	public List<Food> listFoodByBusinessId(Integer businessId) {
		return foodMapper.listFoodByBusinessId(businessId);
	}
}