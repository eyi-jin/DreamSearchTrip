package com.mycom.myboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myboard.dto.UserDto;
import com.mycom.myboard.service.UserService;

// json response
// ResponseEntity 사용 X
@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final int SUCCESS = 1;
	@PostMapping(value="/users")
	public Map<String, String> register(UserDto userDto) {
		Map<String, String> map = new HashMap<>();
		if(userService.userRegister(userDto) == SUCCESS) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
}
