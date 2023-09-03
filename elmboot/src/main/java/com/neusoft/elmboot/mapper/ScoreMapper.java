package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.Score;

@Mapper
public interface ScoreMapper {
	
	@Select("Select sum(scoreCount) from score where userId = #{userId}")
	public int getTotalScore(String userId);
	
	@Select("Select * from score where userId = #{userId} order by createDate")
	public List<Score> listScoreDetial(String userId);
	
	@Delete("Delete from score where scoreId = #{scoreId}")
	public int removeScore(int scoreId);
	
	@Update("Update score set leftTime = #{leftTime} where scoreId = #{scoreId}")
	public int updateScore(Score score);
}
