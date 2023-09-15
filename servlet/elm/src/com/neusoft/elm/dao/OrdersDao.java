package com.neusoft.elm.dao;

import java.util.List;
import com.neusoft.elm.po.Orders;

public interface OrdersDao {
	
	// 向订单表中插入一条记录
	public int saveOrders(Orders orders) throws Exception;
	
	// 根据订单编号查询订单
	public Orders getOrdersById(Integer orderId) throws Exception; 
	
	// 根据用户编号查询该用户的所有订单
	public List<Orders> listOrdersByUserId(String userId) throws Exception; 
	
}