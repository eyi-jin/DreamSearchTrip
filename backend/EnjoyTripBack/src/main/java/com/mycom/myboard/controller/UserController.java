package com.mycom.myboard.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
//@CrossOrigin(
//		origins="*", 
//		allowCredentials="true",
//		allowedHeaders="*",
//		methods= {RequestMethod.GET
//				, RequestMethod.POST
//				, RequestMethod.PUT
//				, RequestMethod.DELETE
//				, RequestMethod.OPTIONS
//				,RequestMethod.HEAD})
public class UserController {
	
	@Autowired
	UserService userService;
	
	private static final int SUCCESS = 1;
	@PostMapping(value="/users")
	public Map<String, String> register(@RequestBody UserDto userDto) {
		Map<String, String> map = new HashMap<>();
		if(userService.userRegister(userDto) == SUCCESS) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
	@GetMapping(value="/users/emailcheck/{userEmail}")
	public Map<String, String> emailCheck(@PathVariable String userEmail){
		Map<String, String> map = new HashMap<>();
		// 아이디 중복 체크 하기
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
	@GetMapping(value="/users/idcheck/{userEmail}")
	public Map<String, String> userIdCheck(@PathVariable(value="userEmail") String userEmail){
		Map<String, String> map = new HashMap<>();
		if(userService.userIdCheck(userEmail) == 0) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
}
