package com.neusoft.elmboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.elmboot.po.Score;
import com.neusoft.elmboot.service.ScoreService;

@RestController
@RequestMapping("/ScoreController")
public class ScoreController {
	
	@Autowired
	private ScoreService scoreService;
	
	// 根据用户编号获取总积分数
	@RequestMapping("/getTotalScore")
	public int getTotalScore(String userId) {
		return scoreService.getTotalScore(userId);
	}
	
	// 根据用户编号获取积分明细
	@RequestMapping("/listScoreDetial")
	public List<Score> listScoreDetial(String userId) {
		return scoreService.listScoreDetial(userId);
	}
}
