package com.neusoft.elmboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.neusoft.elmboot.po.OrderDetailet;

@Mapper
public interface OrderDetailetMapper {

	// 向订单明细表中插入订单明细
	public int saveOrderDetailetBatch(List<OrderDetailet> list);
	 
	// 根据订单编号查询订单明细
	public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderOd);

}
