package com.neusoft.elmboot.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.neusoft.elmboot.mapper.CartMapper;
import com.neusoft.elmboot.mapper.OrderDetailetMapper;
import com.neusoft.elmboot.mapper.OrdersMapper;
import com.neusoft.elmboot.mapper.ScoreMapper;
import com.neusoft.elmboot.po.Cart;
import com.neusoft.elmboot.po.OrderDetailet;
import com.neusoft.elmboot.po.Orders;
import com.neusoft.elmboot.po.Score;
import com.neusoft.elmboot.service.OrdersService;
import com.neusoft.elmboot.util.CommonUtil;

@Service
public class OrdersServiceImpl implements OrdersService{

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderDetailetMapper orderDetailetMapper;
	@Autowired
	private ScoreMapper scoreMapper;
	
	@Override
	@Transactional
	public int createOrders(Orders orders) {
		//1、查询当前用户购物车中当前商家的所有食品
		 Cart cart = new Cart();
		 cart.setUserId(orders.getUserId());
		 cart.setBusinessId(orders.getBusinessId());
		 List<Cart> cartList = cartMapper.listCart(cart);
		//2、创建订单（返回生成的订单编号）
		 orders.setOrderDate(CommonUtil.getCurrentDate());
		 ordersMapper.saveOrders(orders);
		 int orderId = orders.getOrderId();
		 
		 //3、批量添加订单明细
		 List<OrderDetailet> list = new ArrayList<>();
		 for(Cart c : cartList) {
		 OrderDetailet od = new OrderDetailet();
		 od.setOrderId(orderId);
		 od.setFoodId(c.getFoodId());
		 od.setQuantity(c.getQuantity());
		 list.add(od);
		 }
		 orderDetailetMapper.saveOrderDetailetBatch(list);
		 
		 //4、从购物车表中删除相关食品信息
		 cartMapper.removeCart(cart);
		 
		 return orderId;
	}

	@Override
	public Orders getOrdersById(Integer orderId) {
		return ordersMapper.getOrdersById(orderId);
	}

	@Override
	public List<Orders> listOrdersByUserId(String userId){
		return ordersMapper.listOrdersByUserId(userId);
	}

	
	@Override
	public int payOrders(Integer orderId,Integer usedScore) {
		//1.从订单表中更新支付状态
		int a = ordersMapper.updateOrderState(orderId);
		int b = 0;
		//2.向积分表插入一条积分记录
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Orders orders = new Orders();
		orders = ordersMapper.getOrdersById(orderId);
		Score score = new Score();
		score.setUserId(orders.getUserId());
		score.setScoreCount((int)Math.round(orders.getOrderTotal()));
		try {
			score.setCreateDate(sdf.format(sdf.parse(CommonUtil.getCurrentDate())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		score.setLeftTime(30);
		Score score2 = scoreMapper.isTheSameDay(score);
		if(score2==null) {
			b = scoreMapper.insertScore(score);
		}else {
			score.setScoreCount(score.getScoreCount()+score2.getScoreCount());
		    b = scoreMapper.updateScore(score);
		}
		return a&b;
	}

}
