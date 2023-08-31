package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.neusoft.elmboot.po.DeliveryAddress;

@Mapper
public interface DeliveryAddressMapper {
	
	@Select("select * from deliveryAddress where userId=#{userId} order by daId")
	 public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
	
	
}
