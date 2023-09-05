package com.neusoft.elmboot.service;

import java.util.List;
import com.neusoft.elmboot.po.Cart;

public interface CartService {
	
	// 查询此购物车对应用户的所有商家的购物车
	public List<Cart> listCart(Cart cart);

	// 向购物车表中添加一条记录
	public int saveCart(Cart cart);

	// 更新购物车表的一条记录
	public int updateCart(Cart cart);
	
	// 删除购物车表的一条记录
	public int removeCart(Cart cart);
}