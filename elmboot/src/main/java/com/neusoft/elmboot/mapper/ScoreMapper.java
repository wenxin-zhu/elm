package com.neusoft.elmboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
	
	@Insert("insert into score values(null,#{userId},#{scoreCount},#{createDate},#{leftTime})")
	public int insertScore(Score score);
	
	@Select("select * from score where createDate=#{createDate} and userId=#{userId}")
	public Score getScoreByUserIdByCreateDate(Score score);
	
	@Update("update score set scoreCount=#{scoreCount},leftTime=#{leftTime} where createDate=#{createDate} and userId=#{userId}")
	public int updateScore(Score score);
}
