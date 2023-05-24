package com.mycom.myboard.service;

import java.util.List;

import com.mycom.myboard.dto.TodoDto;

public interface TodoService {
	int todoRegist(TodoDto todoDto);

	int todoDelete(int todoId);

	List<TodoDto> todoView(int userSeq);
}
