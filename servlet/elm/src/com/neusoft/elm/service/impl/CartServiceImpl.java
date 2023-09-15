package com.neusoft.elm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.elm.dao.CartDao;
import com.neusoft.elm.dao.impl.CartDaoImpl;
import com.neusoft.elm.po.Cart;
import com.neusoft.elm.service.CartService;
import com.neusoft.elm.util.DBUtil;

public class CartServiceImpl implements CartService{

	// 向购物车表中添加一条记录
	@Override
	public int saveCart(Cart cart) {
		int result = 0;
		CartDao dao = new CartDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.saveCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}

	// 更新购物车表的一条记录
	@Override
	public int updateCart(Cart cart) {
		int result = 0;
		CartDao dao = new CartDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.updateCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}

	// 删除购物车表的一条记录
	@Override
	public int removeCart(Cart cart) {
		int result = 0;
		CartDao dao = new CartDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.removeCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}

	// 查询此购物车对应用户的所有商家的购物车
	@Override
	public List<Cart> listCart(Cart cart) {
		List<Cart> list = new ArrayList();
		CartDao dao = new CartDaoImpl();
		try {
			DBUtil.getConnection();
			list = dao.listCart(cart);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}

}
