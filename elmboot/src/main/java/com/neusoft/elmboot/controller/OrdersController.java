package com.neusoft.elmboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.service.OrdersService;

@RestController
@RequestMapping("/OrdersController")
public class OrdersController {

	@Autowired
	private OrdersService ordersService;
	
	// 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，
	// 并获取自动生成的订单编号，
	// 然后根据用户编号、商家编号从购物车表中查询所有数据，批量添加到订单明细表中，
	// 然后根据用户编号、商家编号删除购物车表中的数据。
	@RequestMapping("/createOrders")
	public int createOrders(Orders orders) throws Exception {
		return ordersService.createOrders(orders);
	}

	// 根据订单编号查询订单信息，包括所属商家信息，和此订单的所有订单明细信息
	@RequestMapping("/getOrdersById")
	public Orders getOrdersById(Orders orders) throws Exception {
		return ordersService.getOrdersById(orders.getOrderId());
	}

	// 根据用户编号查询此用户的所有订单信息
	@RequestMapping("/listOrdersByUserId")
	public List<Orders> listOrdersByUserId(Orders orders) throws Exception {
		return ordersService.listOrdersByUserId(orders.getUserId());
	}
	
	// 支付订单，并扣除和新增相应数量的积分
	@RequestMapping("/payOrders")
	public int payOrders(Integer orderId,Integer usedScore) throws Exception {
		return ordersService.payOrders(orderId,usedScore);
	}
}
