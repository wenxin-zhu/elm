package com.neusoft.elm.dao;


import java.util.List;
import com.neusoft.elm.po.Business;

public interface BusinessDao {
	// 全查询商家表
	public List<Business> listBusiness();
//	public List<Business> listBusiness(String businessName,String businessAddress);
}