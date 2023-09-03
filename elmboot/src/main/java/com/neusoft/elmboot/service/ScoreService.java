package com.neusoft.elmboot.service;

import java.util.List;

import com.neusoft.elmboot.po.Score;

public interface ScoreService {
	
	public int getTotalScore(String userId);
	
	public List<Score> listScoreDetial(String userId);
	
	public int updateAndRemoveScore(String userId);
}
