package com.neusoft.elmboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.DeliveryAddressMapper;
import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.service.DeliveryAddressService;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService{
	
	@Autowired
	private DeliveryAddressMapper deliveryAddressMapper;

	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		return deliveryAddressMapper.listDeliveryAddressByUserId(userId);
	}

	@Override
	public DeliveryAddress getDeliveryAddressById(Integer daId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeDeliveryAddress(Integer daId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
