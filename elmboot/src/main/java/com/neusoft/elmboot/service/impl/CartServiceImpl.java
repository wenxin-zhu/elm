package com.neusoft.elmboot.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;

	// 查询此购物车对应用户的所有商家的购物车
	@Override
	public List<Cart> listCart(Cart cart) {
		return cartMapper.listCart(cart);
	}

	// 向购物车表中添加一条记录
	@Override
	public int saveCart(Cart cart) {
		return cartMapper.saveCart(cart);
	}

	// 更新购物车表的一条记录
	@Override
	public int updateCart(Cart cart) {
		return cartMapper.updateCart(cart);
	}

	// 删除购物车表的一条记录
	@Override
	public int removeCart(Cart cart) {
		return cartMapper.removeCart(cart);
	}
}