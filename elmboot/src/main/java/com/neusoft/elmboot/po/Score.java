package com.neusoft.elmboot.po;

public class Score {
	private Integer scoreId; // 积分编号
	private String userId; // 用户编号
	private Integer scoreCount; // 积分数量
	private String createDate; // 创建时间
	private Integer leftTime; // 剩余过期时间
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(Integer scoreCount) {
		this.scoreCount = scoreCount;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(Integer leftTime) {
		this.leftTime = leftTime;
	}
	
}
