package com.neusoft.elm.dao;

import com.neusoft.elm.po.Admin;

public interface AdminDao {
	
	// 根据管理员用户名和密码获取管理员对象
	public Admin getAdminByNameByPass(String adminName,String password);
}
