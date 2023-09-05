package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import com.neusoft.elmboot.po.Orders;

@Mapper
public interface OrdersMapper {

	// 根据用户编号、商家编号、订单总金额、送货地址编号向订单表中添加一条记录， 并返回自动生成的订单编号，
	@Insert("insert into orders(userId,businessId,orderDate,orderTotal,daId,orderState) values(#{userId},#{businessId},#{orderDate},#{orderTotal},#{daId},0)")
	@Options(useGeneratedKeys=true,keyProperty = "orderId",keyColumn="orderId")
	public int saveOrders(Orders orders);
	
	// 根据订单编号查询订单信息
	public Orders getOrdersById(Integer orderId);
	
	// 根据用户编号查询此用户的所有订单信息
	public List<Orders> listOrdersByUserId(String userId);
	
	// 根据订单编号把订单修改为已支付
	@Update("update orders set orderState=1 where orderId=#{orderId}")
	public int updateOrderState (Integer orderId);

}
