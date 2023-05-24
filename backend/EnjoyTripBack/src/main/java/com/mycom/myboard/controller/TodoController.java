package com.mycom.myboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myboard.dto.TodoDto;
import com.mycom.myboard.dto.UserDto;
import com.mycom.myboard.service.TodoService;

@RestController
public class TodoController{

	@Autowired
	TodoService todoService;
	
	private static final int SUCCESS = 1;
	
	@PostMapping(value="/todo/todoRegist")
	protected Map<String, String> todoRegist( @RequestBody TodoDto todoDto, HttpServletRequest request) {
		Map<String, String>map = new HashMap<>();
		
		todoDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		System.out.println("todoDto: "+ todoDto);
		
		if( todoService.todoRegist(todoDto) == SUCCESS ) {
			map.put("todoContent", todoDto.getTodoContent());
			map.put("userSeq", String.valueOf(todoDto.getUserSeq()));
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
 
	@DeleteMapping(value="/todo/todoDelete/{todoId}")
	protected Map<String, String> favDelete( @PathVariable int todoId ) {
		
		Map<String, String> map = new HashMap<>();
		
		if( todoService.todoDelete(todoId) == SUCCESS ) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}

	@GetMapping(value="/todo/todoView")
	protected List<TodoDto> todoView( HttpServletRequest request ) {
		
		int userSeq = ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq();
		
		return todoService.todoView(userSeq);
		
	}

}
