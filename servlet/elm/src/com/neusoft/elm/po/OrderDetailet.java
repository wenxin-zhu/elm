package com.neusoft.elm.po;

// 对应订单明细表
public class OrderDetailet {

	private Integer odId; // 订单明细编号
	private Integer orderId; // 所属订单编号
	private Integer foodId; // 所属食品编号
	private Integer quantity; // 数量
	 
	// 多对一：所属食品
	private Food food;

	public Integer getOdId() {
		return odId;
	}

	public void setOdId(Integer odId) {
		this.odId = odId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}	
}
