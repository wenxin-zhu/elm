package com.neusoft.elmboot.controller;

import java.util.List;

import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.elmboot.po.DeliveryAddress;
import com.neusoft.elmboot.service.DeliveryAddressService;

@RestController
@RequestMapping("/DeliveryAddressController")
public class DeliveryAddressController {

	@Autowired
	private DeliveryAddressService deliveryAddressService;

	// 根据用户编号查询所属送货地址
	@RequestMapping("/listDeliveryAddressByUserId")
	public List<DeliveryAddress> listDeliveryAddressByUserId(DeliveryAddress deliveryAddress) throws Exception {
		return deliveryAddressService.listDeliveryAddressByUserId(deliveryAddress.getUserId());
	}

	// 根据送货地址编号查询送货地址
	@RequestMapping("/getDeliveryAddressById")
	public DeliveryAddress getDeliveryAddressById(DeliveryAddress deliveryAddress) throws Exception {
		return deliveryAddressService.getDeliveryAddressById(deliveryAddress.getDaId());
	}

	// 向送货地址表中添加一条记录
	@RequestMapping("/saveDeliveryAddress")
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
		int a =deliveryAddressService.saveDeliveryAddress(deliveryAddress);
		System.out.println(CommonUtil.getCurrentDate() +" 用户("+deliveryAddress.getUserId()+") 创建地址 "+deliveryAddress.getAddress());
		return a;
	}

	// 根据送货地址编号更新送货地址信息
	@RequestMapping("/updateDeliveryAddress")
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
		System.out.println(CommonUtil.getCurrentDate()+" 用户("+deliveryAddress.getUserId()+") 修改地址 "+deliveryAddress.getAddress()+"("+deliveryAddress.getDaId()+")");
		return deliveryAddressService.updateDeliveryAddress(deliveryAddress);
	}

	// 根据送货地址编号删除一条记录
	@RequestMapping("/removeDeliveryAddress")
	public int removeDeliveryAddress(DeliveryAddress deliveryAddress) throws Exception {
		return deliveryAddressService.removeDeliveryAddress(deliveryAddress.getDaId());
	}
}
