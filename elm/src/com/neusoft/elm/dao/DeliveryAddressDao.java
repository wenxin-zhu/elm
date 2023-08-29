package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.DeliveryAddress;

public interface DeliveryAddressDao {
	
	// 根据用户编号查询所属送货地址
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception;
	
	// 向送货地址表中添加一条记录
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception;
	
	// 根据送货地址编号查询送货地址
	public DeliveryAddress getDeliveryAddressById(Integer daId) throws Exception;
	
	// 根据送货地址编号更新送货地址信息
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception;
	
	// 根据送货地址编号删除一条记录
	public int removeDeliveryAddress(Integer daId) throws Exception;

}
