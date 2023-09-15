package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Cart;

public interface CartDao {
	
	// 向购物车表中添加一条记录
	public int saveCart(Cart cart) throws Exception;
	
	// 更新购物车表的一条记录
	public int updateCart(Cart cart) throws Exception;
	
	// 删除购物车表的一条记录
	public int removeCart(Cart cart) throws Exception;
	
	// 查询此购物车对应用户的所有商家的购物车
	public List<Cart> listCart(Cart cart) throws Exception;
}
