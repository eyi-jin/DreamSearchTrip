package com.mycom.myboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myboard.dto.FavDto;
import com.mycom.myboard.dto.TodoDto;

@Mapper
public interface TodoDao {
	int todoRegist(TodoDto todoDto);

	int todoDelete(int todoId);

	List<TodoDto> todoView(int userSeq);

}
