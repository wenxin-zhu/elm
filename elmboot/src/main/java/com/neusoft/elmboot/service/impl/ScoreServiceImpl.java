package com.neusoft.elmboot.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.elmboot.mapper.ScoreMapper;
import com.neusoft.elmboot.po.Score;
import com.neusoft.elmboot.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {
	
	@Autowired
	private ScoreMapper scoreMapper;

	// 根据用户编号获取总积分数
	@Override
	public int getTotalScore(String userId) {
		updateAndRemoveScore(userId);
		int totalScore = 0;
		List<Integer> list = scoreMapper.getTotalScore(userId);
		for (int i = 0; i < list.size(); i++) {
			totalScore += list.get(i);
		}
		return totalScore;
	}
	
	// 根据用户编号获取积分明细
	@Override
	public List<Score> listScoreDetial(String userId) {
		updateAndRemoveScore(userId);
		return scoreMapper.listScoreDetial(userId);
	}
	
	// 更新积分的过期时间，删除过期的积分记录
	@Override
	public int updateAndRemoveScore(String userId) {
		List<Score> list = scoreMapper.listScoreDetial(userId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < list.size(); i++) {
	        Date createDate = new Date();
	        // 获取今天的日期对象
	        Date todayDate = new Date();
			try {
				// 调用方法parse,字符串转成日期对象
				createDate = sdf.parse(list.get(i).getCreateDate());
				todayDate = sdf.parse(sdf.format(todayDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        // 将两个日期转成毫秒值,Date类的方法getTime
	        long createSecond = createDate.getTime();
	        long todaySecond = todayDate.getTime();
	        int leftTime = 29 - (int)((todaySecond - createSecond) / (24 * 60 * 60 * 1000));
	        if (leftTime > 0) {
	        	list.get(i).setLeftTime(leftTime);
	        	scoreMapper.updateScore(list.get(i));
	        } else {
	        	scoreMapper.removeScore(list.get(i).getScoreId());
	        }
        }
        return 0;
	}
}
