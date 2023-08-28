package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.DeliveryAddress;

public interface DeliveryAddressDao {
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) throws Exception;
}
