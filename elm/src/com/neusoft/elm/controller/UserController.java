package com.neusoft.elm.controller;

import javax.servlet.http.HttpServletRequest;
import com.neusoft.elm.po.User;
import com.neusoft.elm.service.UserService;
import com.neusoft.elm.service.impl.UserServiceImpl;

public class UserController {
		
	// 根据用户编号与密码查询用户信息
	public Object getUserByIdByPass(HttpServletRequest request) throws Exception{
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		UserService service = new UserServiceImpl();
		User user = service.getUserByIdByPass(userId, password);
		return user;
	}
	
	// 根据用户编号查询用户表返回的行数
	public Object getUserById(HttpServletRequest request) throws Exception{
		String userId = request.getParameter("userId");
		UserService service = new UserServiceImpl();
		int result = service.getUserById(userId);
		return result;
	}
	
	// 向用户表中添加一条记录
	public Object saveUser(HttpServletRequest request) throws Exception{
		User user = new User();
		user.setUserId(request.getParameter("userId"));
		user.setPassword(request.getParameter("password"));
		user.setUserName(request.getParameter("userName"));
		user.setUserSex(Integer.valueOf(request.getParameter("userSex")));
		UserService service = new UserServiceImpl();
		int result = service.saveUser(user);
		return result;
	}

}