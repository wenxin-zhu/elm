package com.neusoft.elm.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.neusoft.elm.po.Orders;
import com.neusoft.elm.service.OrdersService;
import com.neusoft.elm.service.impl.OrdersServiceImpl;

public class OrdersController {
	
	// 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，
	// 并获取自动生成的订单编号，
	// 然后根据用户编号、商家编号从购物车表中查询所有数据，批量添加到订单明细表中，
	// 然后根据用户编号、商家编号删除购物车表中的数据。
	public Object createOrders(HttpServletRequest request) throws Exception{
		String userId = request.getParameter("userId");
		Integer businessId = Integer.valueOf(request.getParameter("businessId"));
		Integer daId = Integer.valueOf(request.getParameter("daId"));
		Double orderTotal = Double.valueOf(request.getParameter("orderTotal"));
		OrdersService service = new OrdersServiceImpl();
		int orderId = service.createOrders(userId, businessId, daId, orderTotal);
		return orderId;
	}
	
	// 根据订单编号查询订单信息，包括所属商家信息，和此订单的所有订单明细信息
	public Object getOrdersById(HttpServletRequest request) throws Exception{
		Integer orderId = Integer.valueOf(request.getParameter("orderId"));
		OrdersService service = new OrdersServiceImpl();
		Orders orders = service.getOrdersById(orderId);
		return orders;
	}
	 
	// 根据用户编号查询此用户的所有订单信息
	public Object listOrdersByUserId(HttpServletRequest request) throws Exception{
		String userId = request.getParameter("userId");
		OrdersService service = new OrdersServiceImpl();
		List<Orders> list = service.listOrdersByUserId(userId);
		return list;
	}
}