package com.neusoft.elm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.DeliveryAddress;
import com.neusoft.elm.service.DeliveryAddressService;
import com.neusoft.elm.service.impl.DeliveryAddressServiceImpl;

public class DeliveryAddressController {

	public Object listDeliveryAddressByUserId(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userId");
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		List<DeliveryAddress> list = service.listDeliveryAddressByUserId(userId);
		return list;
	}
}
