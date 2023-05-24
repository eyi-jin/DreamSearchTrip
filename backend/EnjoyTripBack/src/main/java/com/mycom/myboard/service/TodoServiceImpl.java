package com.mycom.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myboard.dao.TodoDao;
import com.mycom.myboard.dto.TodoDto;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoDao todoDao;

	@Override
	public int todoRegist(TodoDto todoDto) {
		return todoDao.todoRegist(todoDto);
	}
	

	@Override
	public int todoUpdate(TodoDto todoDto) {
		return todoDao.todoUpdate(todoDto);
	}

	@Override
	public int todoDelete(int todoId) {
		return todoDao.todoDelete(todoId);

	}

	@Override
	public List<TodoDto> todoView(int userSeq) {
		return todoDao.todoView(userSeq);
	}


}
