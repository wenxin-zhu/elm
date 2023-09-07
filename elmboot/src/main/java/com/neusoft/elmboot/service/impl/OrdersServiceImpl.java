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
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private OrdersMapper ordersMapper;
	@Autowired
	private OrderDetailetMapper orderDetailetMapper;
	@Autowired
	private ScoreMapper scoreMapper;

	// 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录，
	// 并获取自动生成的订单编号，
	// 然后根据用户编号、商家编号从购物车表中查询所有数据，批量添加到订单明细表中，
	// 然后根据用户编号、商家编号删除购物车表中的数据。
	@Override
	@Transactional
	public int createOrders(Orders orders) {

		// 1、查询当前用户购物车中当前商家的所有食品
		Cart cart = new Cart();
		cart.setUserId(orders.getUserId());
		cart.setBusinessId(orders.getBusinessId());
		List<Cart> cartList = cartMapper.listCart(cart);

		// 2、创建订单（返回生成的订单编号）
		orders.setOrderDate(CommonUtil.getCurrentDate());
		ordersMapper.saveOrders(orders);
		int orderId = orders.getOrderId();

		// 3、批量添加订单明细
		List<OrderDetailet> list = new ArrayList<OrderDetailet>();
		for (Cart c : cartList) {
			OrderDetailet od = new OrderDetailet();
			od.setOrderId(orderId);
			od.setFoodId(c.getFoodId());
			od.setQuantity(c.getQuantity());
			list.add(od);
		}
		orderDetailetMapper.saveOrderDetailetBatch(list);

		// 4、从购物车表中删除相关食品信息
		cartMapper.removeCart(cart);

		return orderId;
	}

	// 根据订单编号查询订单信息，包括所属商家信息，和此订单的所有订单明细信息
	@Override
	public Orders getOrdersById(Integer orderId) {
		return ordersMapper.getOrdersById(orderId);
	}

	// 根据用户编号查询此用户的所有订单信息
	@Override
	public List<Orders> listOrdersByUserId(String userId) {
		return ordersMapper.listOrdersByUserId(userId);
	}

	// 支付订单，并扣除和新增相应数量的积分
	@Override
	public int payOrders(Integer orderId, Integer usedScore) {
		
		// 1.从订单表中更新支付状态
		int a = ordersMapper.updateOrderState(orderId);
		int b = 0;
		
		// 2.删除用掉的积分
		String userId = getOrdersById(orderId).getUserId();
		List<Score> list = scoreMapper.listScoreDetial(userId);
		for (int i = 0; i < list.size() && usedScore > 0; i++) {
			if (usedScore >= list.get(i).getScoreCount()) {
				usedScore -= list.get(i).getScoreCount();
				scoreMapper.removeScore(list.get(i).getScoreId());
			} else {
				list.get(i).setScoreCount(list.get(i).getScoreCount() - usedScore);
				usedScore = 0;
				scoreMapper.updateScore(list.get(i));
			}
		}
		
		// 3.向积分表插入一条积分记录
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Orders orders = new Orders();
		orders = ordersMapper.getOrdersById(orderId);
		Score score = new Score();
		score.setUserId(orders.getUserId());
		score.setScoreCount((int) Math.round(orders.getOrderTotal()));
		try {
			score.setCreateDate(sdf.format(sdf.parse(CommonUtil.getCurrentDate())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		score.setLeftTime(30);
		Score score2 = scoreMapper.getScoreByUserIdByCreateDate(score);
		if (score2 == null) {
			b = scoreMapper.insertScore(score);
		} else {
			score.setScoreCount(score.getScoreCount() + score2.getScoreCount());
			b = scoreMapper.updateScore(score);
		}
		return a & b;
	}

}
