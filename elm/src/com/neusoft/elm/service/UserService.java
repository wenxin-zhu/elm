package com.neusoft.elm.service;

import com.neusoft.elm.po.User;

public interface UserService {
	
	// 根据用户编号与密码查询用户信息
	public User getUserByIdByPass(String userId,String password);
	
	// 根据用户编号查询用户表返回的行数
	public int getUserById(String userId);
	
	// 向用户表中添加一条记录
	public int saveUser(User user);
	
}