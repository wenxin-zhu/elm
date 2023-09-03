package com.neusoft.elmboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.neusoft.elmboot.po.Score;
import com.neusoft.elmboot.po.User;

@Mapper
public interface ScoreMapper {

	@Insert("insert into score values(null,#{userId},#{scoreCount},#{createDate},#{leftTime})")
	public int insertScore(Score score);
	
	
}
