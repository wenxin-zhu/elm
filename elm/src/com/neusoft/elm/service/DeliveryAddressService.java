package com.neusoft.elm.service;

import java.util.List;

import com.neusoft.elm.po.DeliveryAddress;

public interface DeliveryAddressService {

	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
}
