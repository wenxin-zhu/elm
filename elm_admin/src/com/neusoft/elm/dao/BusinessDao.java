package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {
	public List<Business> listBusiness();
	public Business getBusinessByIdByPass(Integer businessId,String password);
	public Business getBusinessById(Integer businessId);
	public int updateBusiness(Business business);
	public int updateBusinessByPassword(Integer businessId,String password);
	
}
