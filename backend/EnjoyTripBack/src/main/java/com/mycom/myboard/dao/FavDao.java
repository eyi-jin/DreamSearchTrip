package com.mycom.myboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myboard.dto.FavDto;

@Mapper
public interface FavDao {
	int favRegister(FavDto favDto);

	int favDelete(int favId);

	List<FavDto> favView(int userSeq);

}
