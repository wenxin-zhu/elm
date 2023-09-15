package com.neusoft.elm.po;

// 对应用户表
public class User {

	private String userId; // 用户编号
	private String password; // 密码
	private String userName; // 用户名称
	private Integer userSex; // 用户性别（1：男； 0：女）
	private String userImg; // 用户头像
	private Integer delTag; // 删除标记（1：正常； 0：删除）
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public Integer getDelTag() {
		return delTag;
	}
	public void setDelTag(Integer delTag) {
		this.delTag = delTag;
	}
}
