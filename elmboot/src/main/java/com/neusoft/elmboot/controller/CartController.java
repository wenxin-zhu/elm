package com.neusoft.elmboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.service.CartService;

@RestController
@RequestMapping("/CartController")
public class CartController {
	
	@Autowired
	private CartService cartService;

	// 根据用户编号查询此用户所有购物车信息
	@RequestMapping("/listCart")
	public List<Cart> listCart(Cart cart) throws Exception {
		return cartService.listCart(cart);
	}

	// 向购物车表中添加一条记录
	@RequestMapping("/saveCart")
	public int saveCart(Cart cart) throws Exception {
		return cartService.saveCart(cart);
	}

	// 根据用户编号、商家编号、食品编号更新记录
	@RequestMapping("/updateCart")
	public int updateCart(Cart cart) throws Exception {
		return cartService.updateCart(cart);
	}

	// 根据用户编号、商家编号、食品编号删除购物车表中的一条记录
	@RequestMapping("/removeCart")
	public int removeCart(Cart cart) throws Exception {
		return cartService.removeCart(cart);
	}
}