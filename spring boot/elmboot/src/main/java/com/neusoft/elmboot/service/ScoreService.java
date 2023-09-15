package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.Score;

public interface ScoreService {
	
	// 根据用户编号获取总积分数
	public int getTotalScore(String userId);
	
	// 根据用户编号获取积分明细
	public List<Score> listScoreDetial(String userId);
	
	// 更新积分的过期时间，删除过期的积分记录
	public int updateAndRemoveScore(String userId);
}
