package com.neusoft.elm.dao;

import com.neusoft.elm.po.User;

public interface UserDao {
	
	// 根据用户编号与密码查询用户信息
	public User getUserByIdByPass(String userId,String password) throws Exception;
	
	// 根据用户编号查询用户表返回的行数（用于判断用户是否存在）
	public int getUserById(String userId) throws Exception; 
	
	// 向用户表中添加一条记录
	public int saveUser(User user) throws Exception; 
 
}
