package com.neusoft.elmboot.po;

// 对应购物车表
public class Cart {

	private Integer cartId; // 无意义编号
	private Integer foodId; // 食品编号
	private Integer businessId; // 所属商家编号
	private String userId; // 所属用户编号
	private Integer quantity; // 同一类型食品的购买数量
	
	//多对一：所属食品
	private Food food;
	//多对一：所属商家
	private Business business;
	
	public Integer getCartId() {
		return cartId;
	}
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
