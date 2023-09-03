package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.neusoft.elmboot.po.Score;
import com.neusoft.elmboot.po.User;

@Mapper
public interface ScoreMapper {

	@Insert("insert into score values(null,#{userId},#{scoreCount},#{createDate},#{leftTime})")
	public int insertScore(Score score);
	
	@Select("select * from score where createDate=#{createDate} and userId=#{userId}")
	public Score isTheSameDay(Score score);
	
	@Update("update score set scoreCount=#{scoreCount} where createDate=#{createDate} and userId=#{userId}")
	public int updateScore(Score score);
}
