package com.neusoft.elm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.DeliveryAddress;
import com.neusoft.elm.service.DeliveryAddressService;
import com.neusoft.elm.service.impl.DeliveryAddressServiceImpl;

public class DeliveryAddressController {

	// 根据用户编号查询所属送货地址
	public Object listDeliveryAddressByUserId(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userId");
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		List<DeliveryAddress> list = service.listDeliveryAddressByUserId(userId);
		return list;
	}
	
	// 向送货地址表中添加一条记录
	public Object saveDeliveryAddress(HttpServletRequest request) throws Exception {
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setContactName(request.getParameter("contactName"));
		deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
		deliveryAddress.setContactTel(request.getParameter("contactTel"));
		deliveryAddress.setAddress(request.getParameter("address"));
		deliveryAddress.setUserId(request.getParameter("userId"));
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		int result = service.saveDeliveryAddress(deliveryAddress);
		return result;
	}
	
	// 根据送货地址编号查询送货地址
	public Object getDeliveryAddressById(HttpServletRequest request) throws Exception {
		Integer daId = Integer.valueOf(request.getParameter("daId"));
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		DeliveryAddress deliveryAddress = service.getDeliveryAddressById(daId);
		return deliveryAddress;
	}

	// 根据送货地址编号更新送货地址信息
	public Object updateDeliveryAddress(HttpServletRequest request) throws Exception {
		DeliveryAddress deliveryAddress = new DeliveryAddress();
		deliveryAddress.setContactName(request.getParameter("contactName"));
		deliveryAddress.setContactSex(Integer.valueOf(request.getParameter("contactSex")));
		deliveryAddress.setContactTel(request.getParameter("contactTel"));
		deliveryAddress.setAddress(request.getParameter("address"));
		deliveryAddress.setDaId(Integer.valueOf(request.getParameter("daId")));
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		int result = service.updateDeliveryAddress(deliveryAddress);
		return result;
	}

	// 根据送货地址编号删除一条记录
	public Object removeDeliveryAddress(HttpServletRequest request) throws Exception {
		Integer daId = Integer.valueOf(request.getParameter("daId"));
		DeliveryAddressService service = new DeliveryAddressServiceImpl();
		int result = service.removeDeliveryAddress(daId);
		return result;
	}
}
