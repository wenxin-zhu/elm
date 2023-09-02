package com.neusoft.elm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.Cart;
import com.neusoft.elm.service.CartService;
import com.neusoft.elm.service.impl.CartServiceImpl;

public class CartController {
	
	// 向购物车表中添加一条记录
	public Object saveCart(HttpServletRequest request) throws Exception {
		System.out.println("save");
		System.out.println(request);
		Cart cart = new Cart();
		cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
		cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
		cart.setUserId(request.getParameter("userId"));
		CartService service = new CartServiceImpl();
		int result = service.saveCart(cart);
		System.out.println(result);
		return result;
	}

	// 根据用户编号、商家编号、食品编号更新数量
	public Object updateCart(HttpServletRequest request) throws Exception {
		System.out.println("update");
		System.out.println(request.getParameter("foodId"));
		System.out.println(request.getParameter("businessId"));
		System.out.println(request.getParameter("userId"));
		System.out.println(request.getParameter("quantity"));
		Cart cart = new Cart();
		cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
		cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
		cart.setUserId(request.getParameter("userId"));
		cart.setQuantity(Integer.valueOf(request.getParameter("quantity")));
		CartService service = new CartServiceImpl();
		int result = service.updateCart(cart);
		System.out.println(result);
		return result;
	}
	
	// 根据用户编号、商家编号、食品编号删除购物车表中的一条食品记录
	// 根据用户编号、商家编号删除购物车表中的多条条记录
	public Object removeCart(HttpServletRequest request) throws Exception {
		Cart cart = new Cart();
		cart.setFoodId(Integer.valueOf(request.getParameter("foodId")));
		cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
		cart.setUserId(request.getParameter("userId"));
		CartService service = new CartServiceImpl();
		int result = service.removeCart(cart);
		return result;
	}

	// 根据用户编号查询此用户所有购物车信息
	// 根据用户编号和商家编号，查询此用户购物车中某个商家的所有购物车信息
	public Object listCart(HttpServletRequest request) throws Exception {
		Cart cart = new Cart();
		cart.setUserId(request.getParameter("userId"));
		if (request.getParameter("businessId") != null) {
			cart.setBusinessId(Integer.valueOf(request.getParameter("businessId")));
		}
		CartService service = new CartServiceImpl();
		List<Cart> list = service.listCart(cart);
		return list;
	}
}
