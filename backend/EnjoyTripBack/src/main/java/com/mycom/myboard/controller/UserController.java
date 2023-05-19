package com.mycom.myboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myboard.dto.UserDto;
import com.mycom.myboard.service.UserService;

// json response
// ResponseEntity 사용 X
@RestController
@CrossOrigin(
		origins="http://localhost:5500", 
		allowCredentials="true",
		allowedHeaders="*",
		methods= {RequestMethod.GET
				, RequestMethod.POST
				, RequestMethod.PUT
				, RequestMethod.DELETE
				, RequestMethod.OPTIONS
				,RequestMethod.HEAD})
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
	
	@PostMapping(value="/users/changepwd")
	public Map<String, String> changePwd(@RequestBody UserDto userDto){
		Map<String, String> map = new HashMap<>();
		System.out.println(userDto);
		if(userService.userPasswordChange(userDto) == SUCCESS) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
	@DeleteMapping(value="/users/delete/{userEmail}")
	public Map<String, String> userDelete(@PathVariable(value="userEmail") String userEmail){
		Map<String, String> map = new HashMap<>();
		if(userService.userDelete(userEmail) == SUCCESS) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
}
