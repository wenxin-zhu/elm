package com.neusoft.elm.dao;


import java.util.List;
import com.neusoft.elm.po.Business;

public interface BusinessDao {
	
	public List<Business> listBusiness(String businessName,String businessAddress);
	// 添加商家，返回新添加的商家编号
	public int saveBusiness(String businessName);
	// 删除商家，返回受影响的行数
	public int removeBusiness(int businessId);

	public List<Business> listBusiness();
	public Business getBusinessByIdByPass(Integer businessId,String password);
	public Business getBusinessById(Integer businessId);
	public int updateBusiness(Business business);
	public int updateBusinessByPassword(Integer businessId,String password);
	
}
