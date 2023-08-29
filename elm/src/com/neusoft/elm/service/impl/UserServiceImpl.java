package com.neusoft.elm.service.impl;

import com.neusoft.elm.dao.UserDao;
import com.neusoft.elm.dao.impl.UserDaoImpl;
import com.neusoft.elm.po.User;
import com.neusoft.elm.service.UserService;
import com.neusoft.elm.util.DBUtil;

public class UserServiceImpl implements UserService{
	
	// 根据用户编号与密码查询用户信息
	@Override
	public User getUserByIdByPass(String userId, String password) {
		User user = null;
		UserDao dao = new UserDaoImpl();
		try {
			DBUtil.getConnection();
			user = dao.getUserByIdByPass(userId, password);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
		return user;
	}
	
	// 根据用户编号查询用户表返回的行数
	@Override
	public int getUserById(String userId) {
		int result = 0;
		UserDao dao = new UserDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.getUserById(userId);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
		return result;
	}
	
	// 向用户表中添加一条记录
	@Override
	public int saveUser(User user) {
		int result = 0;
		UserDao dao = new UserDaoImpl();
		try {
			DBUtil.getConnection();
			result = dao.saveUser(user);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close();
		}
		return result;
	}
}