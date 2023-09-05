package com.neusoft.elmboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neusoft.elmboot.mapper.UserMapper;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	// 根据用户编号与密码查询用户信息
	@Override
	public User getUserByIdByPass(User user) {
		return userMapper.getUserByIdByPass(user);
	}

	// 根据用户编号查询用户表返回的行数
	@Override
	public int getUserById(String userId) {
		return userMapper.getUserById(userId);
	}

	// 向用户表中添加一条记录
	@Override
	public int saveUser(User user) {
		return userMapper.saveUser(user);
	}
}