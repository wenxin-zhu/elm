package com.neusoft.elm.dao;

import java.util.List;
import com.neusoft.elm.po.Food;

public interface FoodDao {
	
	// 根据商家编号查询所属食品信息
	public List<Food> listFoodByBusinessId(Integer businessId) throws Exception;
	
}