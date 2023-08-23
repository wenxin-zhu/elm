package com.neusoft.elm.view;



public interface BusinessView {
	
	// 全查询
	public void listBusinessAll();
	// 多条件模糊查询
	public void listBusiness();
	// 添加商家
	public void saveBusiness();
	// 删除商家
	public void removeBusiness();
}
