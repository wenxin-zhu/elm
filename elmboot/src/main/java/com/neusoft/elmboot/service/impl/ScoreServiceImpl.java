package com.neusoft.elmboot.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

	@Override
	public int getTotalScore(String userId) {
		updateAndRemoveScore(userId);
		return scoreMapper.getTotalScore(userId);
	}
	
	@Override
	public List<Score> listScoreDetial(String userId) {
		updateAndRemoveScore(userId);
		return scoreMapper.listScoreDetial(userId);
	}
	
	@Override
	public int updateAndRemoveScore(String userId) {
		List<Score> list = scoreMapper.listScoreDetial(userId);
		List<Score> reList = new ArrayList<Score>();
		// 创建SimpleDateFormat对象,写日期模式
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
//	        System.out.println(createSecond);
//	        System.out.println(todaySecond);
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
