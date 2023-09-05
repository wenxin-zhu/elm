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
	
	// 根据用户编号查询所属送货地址
	@Select("select * from deliveryAddress where userId=#{userId} order by daId")
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId);
	
	// 根据送货地址编号查询送货地址
	@Select("select * from deliveryAddress where daId=#{daId}")
	public DeliveryAddress getDeliveryAddressById(Integer daId);
	
	// 向送货地址表中添加一条记录
	@Insert("insert into deliveryAddress values (null,#{contactName},#{contactSex},#{contactTel},#{address},#{userId})")
	public int saveDeliveryAddress(DeliveryAddress deliveryAddress);
	
	// 根据送货地址编号更新送货地址信息
	@Update("update deliveryAddress set contactName=#{contactName},contactSex=#{contactSex},contactTel=#{contactTel},"
			+ "address=#{address} where daId=#{daId}" )
	public int updateDeliveryAddress(DeliveryAddress deliveryAddress);
	
	// 根据送货地址编号删除一条记录
	@Delete("delete from deliveryAddress where daId=#{daId}")
	public int removeDeliveryAddress(Integer daId);
}
