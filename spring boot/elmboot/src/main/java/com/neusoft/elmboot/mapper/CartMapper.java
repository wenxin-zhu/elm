package com.neusoft.elmboot.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import com.neusoft.elmboot.po.Cart;

@Mapper
public interface CartMapper {
	
	// 根据用户编号查询此用户所有购物车信息
	// 根据用户编号和商家编号，查询此用户购物车中某个商家的所有购物车信息
	public List<Cart> listCart(Cart cart);

	// 向购物车表中添加一条记录
	@Insert("insert into cart values(null,#{foodId},#{businessId},#{userId},1)")
	public int saveCart(Cart cart);

	// 根据用户编号、商家编号、食品编号更新数量
	@Update("update cart set quantity=#{quantity} where foodId=#{foodId} and businessId=#{businessId} and userId=#{userId}")
	public int updateCart(Cart cart);

	// 根据用户编号、商家编号、食品编号删除购物车表中的食品记录
	public int removeCart(Cart cart);
}