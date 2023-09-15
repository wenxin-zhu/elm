package com.neusoft.elmboot.po;

// 对应食品表
public class Food {

	private Integer foodId; // 食品编号
	private String foodName; // 食品名称
	private String foodExplain; // 食品介绍
	private String foodImg; // 食品图片
	private Double foodPrice; // 食品价格
	private Integer businessId; // 所属商家编号
	private String remarks; // 备注
	
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodExplain() {
		return foodExplain;
	}
	public void setFoodExplain(String foodExplain) {
		this.foodExplain = foodExplain;
	}
	public String getFoodImg() {
		return foodImg;
	}
	public void setFoodImg(String foodImg) {
		this.foodImg = foodImg;
	}
	public Double getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(Double foodPrice) {
		this.foodPrice = foodPrice;
	}
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
