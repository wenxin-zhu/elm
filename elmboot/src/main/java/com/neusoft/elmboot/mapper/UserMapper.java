package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.neusoft.elmboot.po.User;

@Mapper
public interface UserMapper {
	
	// 根据用户编号与密码查询用户信息
	@Select("select * from user where userId=#{userId} and password=#{password}")
	public User getUserByIdByPass(User user);

	// 根据用户编号查询用户表返回的行数
	@Select("select count(*) from user where userId=#{userId}")
	public int getUserById(String userId);

	// 向用户表中添加一条记录
	@Insert("insert into user values(#{userId},#{password},#{userName},#{userSex},null,1)")
	public int saveUser(User user);
}