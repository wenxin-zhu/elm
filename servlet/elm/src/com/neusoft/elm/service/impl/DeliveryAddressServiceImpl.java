package com.neusoft.elm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.elm.dao.DeliveryAddressDao;
import com.neusoft.elm.dao.impl.DeliveryAddressDaoImpl;
import com.neusoft.elm.po.DeliveryAddress;
import com.neusoft.elm.service.DeliveryAddressService;
import com.neusoft.elm.util.DBUtil;

public class DeliveryAddressServiceImpl implements DeliveryAddressService{

	// 根据用户编号查询所属送货地址
	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		List<DeliveryAddress> list = new ArrayList<>();
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			list = dao.listDeliveryAddressByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}

	// 向送货地址表中添加一条记录
	@Override
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress) {
		int result = 0;
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.saveDeliveryAddress(deliveryAddress);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;

	}

	// 根据送货地址编号查询送货地址
	@Override
	public DeliveryAddress getDeliveryAddressById(Integer daId) {
		DeliveryAddress deliveryAddress = null;
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			deliveryAddress = dao.getDeliveryAddressById(daId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return deliveryAddress;
	}

	// 更新送货地址信息
	@Override
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress) {
		int result = 0;
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.updateDeliveryAddress(deliveryAddress);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}

	// 根据送货地址编号删除一条记录
	@Override
	public int removeDeliveryAddress(Integer daId) {
		int result = 0;
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.removeDeliveryAddress(daId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return result;
	}
}
