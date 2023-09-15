package com.neusoft.elm.dao;

import java.util.List;
import com.neusoft.elm.po.OrderDetailet;

public interface OrderDetailetDao {
	
	// 向订单明细表添加订单明细信息
	public int saveOrderDetailetBatch(List<OrderDetailet> list) throws Exception;
	
	// 根据订单编号查询订单明细信息
	public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderId) throws Exception;
	
}