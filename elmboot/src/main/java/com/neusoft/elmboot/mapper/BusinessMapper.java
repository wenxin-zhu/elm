package com.neusoft.elmboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.neusoft.elmboot.po.Business;

@Mapper
public interface BusinessMapper {
	
	// 根据点餐分类编号查询所属商家信息
	@Select("select * from business where orderTypeId=#{orderTypeId} order by businessId")
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId);
 
	// 根据商家编号查询商家信息
	@Select("select * from business where businessId=#{businessId}")
	public Business getBusinessById(Integer businessId);
}
