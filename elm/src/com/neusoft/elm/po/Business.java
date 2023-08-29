package com.neusoft.elm.po;

// 对应商家表
public class Business {

	private Integer businessId; // 商家编号
	private String businessName; // 商家名称
	private String businessAddress; // 商家地址
	private String businessExplain; // 商家介绍
	private String businessImg; // 商家图片
	private Integer orderTypeId; // 点餐分类
	private double starPrice; // 起送费
	private double deliveryPrice; // 配送费
	private String remarks; // 备注
	
	public Integer getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getBusinessExplain() {
		return businessExplain;
	}
	public void setBusinessExplain(String businessExplain) {
		this.businessExplain = businessExplain;
	}
	public String getBusinessImg() {
		return businessImg;
	}
	public void setBusinessImg(String businessImg) {
		this.businessImg = businessImg;
	}
	public Integer getOrderTypeId() {
		return orderTypeId;
	}
	public void setOrderTypeId(Integer orderTypeId) {
		this.orderTypeId = orderTypeId;
	}
	public double getStarPrice() {
		return starPrice;
	}
	public void setStarPrice(double starPrice) {
		this.starPrice = starPrice;
	}
	public double getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
