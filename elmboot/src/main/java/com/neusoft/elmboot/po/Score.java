package com.neusoft.elmboot.po;

public class Score {
	
	private int scoreId;
	private String userId;
	private int scoreCount;
	private String createDate;
	private int leftTime;
	public int getScoreId() {
		return scoreId;
	}
	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getScoreCount() {
		return scoreCount;
	}
	public void setScoreCount(int scoreCount) {
		this.scoreCount = scoreCount;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getLeftTime() {
		return leftTime;
	}
	public void setLeftTime(int leftTime) {
		this.leftTime = leftTime;
	}
	
}
