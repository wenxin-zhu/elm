package com.neusoft.elm.view;
import com.neusoft.elm.po.Business;


public interface BusinessView {
	
	// 全查询
	public void listBusinessAll();
	// 多条件模糊查询
	public void listBusiness();
	// 添加商家
	public void saveBusiness();
	// 删除商家
	public void removeBusiness();

	public Business login();
	public void showBusiness(Integer businessId);
	public void editBusiness(Integer businessId);
	public void updateBusinessByPassword(Integer businessId);
}
