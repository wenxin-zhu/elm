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
	
	// 根据用户编号获取所有积分记录的积分数量
	@Select("Select scoreCount from score where userId = #{userId}")
	public List<Integer> getTotalScore(String userId);
	
	// 根据用户编号获取所有积分记录
	@Select("Select * from score where userId = #{userId} order by createDate")
	public List<Score> listScoreDetial(String userId);
	
	// 根据积分编号删除积分记录
	@Delete("Delete from score where scoreId = #{scoreId}")
	public int removeScore(int scoreId);
	
	// 向积分表中插入积分记录
	@Insert("insert into score values(null,#{userId},#{scoreCount},#{createDate},#{leftTime})")
	public int insertScore(Score score);
	
	// 根据创建时间和用户编号查询积分记录
	@Select("select * from score where createDate=#{createDate} and userId=#{userId}")
	public Score getScoreByUserIdByCreateDate(Score score);
	
	// 更新积分记录
	@Update("update score set scoreCount=#{scoreCount},leftTime=#{leftTime} where createDate=#{createDate} and userId=#{userId}")
	public int updateScore(Score score);
}
