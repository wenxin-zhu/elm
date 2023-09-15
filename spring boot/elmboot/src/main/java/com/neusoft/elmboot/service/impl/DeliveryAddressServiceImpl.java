package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.DeliveryAddressMapper;
import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.service.DeliveryAddressService;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;

	// 根据用户编号查询所属送货地址
	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		return deliveryAddressMapper.listDeliveryAddressByUserId(userId);
	}

	// 根据送货地址编号查询送货地址
	@Override
	public DeliveryAddress getDeliveryAddressById(Integer daId) {
		return deliveryAddressMapper.getDeliveryAddressById(daId);
	}

	// 向送货地址表中添加一条记录
	@Override
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		return deliveryAddressMapper.saveDeliveryAddress(deliveryAddress);
	}

	// 更新送货地址信息
	@Override
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		return deliveryAddressMapper.updateDeliveryAddress(deliveryAddress);
	}

	// 根据送货地址编号删除一条记录
	@Override
	public int removeDeliveryAddress(Integer daId) {
		return deliveryAddressMapper.removeDeliveryAddress(daId);
	}

}
