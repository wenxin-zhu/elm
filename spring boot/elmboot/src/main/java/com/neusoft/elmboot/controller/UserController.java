package com.neusoft.elmboot.controller;

import com.neusoft.elmboot.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neusoft.elmboot.po.User;
import com.neusoft.elmboot.service.UserService;

@RestController
@RequestMapping("/UserController")
public class UserController {

	@Autowired
	private UserService userService;

	// 根据用户编号与密码查询用户信息
	@RequestMapping("/getUserByIdByPass")
	public User getUserByIdByPass(User user) throws Exception {
		User use=userService.getUserByIdByPass(user);
		if(use!=null){
			System.out.println(CommonUtil.getCurrentDate()+ " "+use.getUserName()+"("+use.getUserId()+") "+"登录");
		}
		return userService.getUserByIdByPass(user);
	}

	// 根据用户编号查询用户表返回的行数
	@RequestMapping("/getUserById")
	public int getUserById(User user) throws Exception {
		return userService.getUserById(user.getUserId());
	}

	// 向用户表中添加一条记录
	@RequestMapping("/saveUser")
	public int saveUser(User user) throws Exception {
		System.out.println(CommonUtil.getCurrentDate()+" "+user.getUserName()+"("+user.getUserId()+")"+" 注册");
		return userService.saveUser(user);
	}
}