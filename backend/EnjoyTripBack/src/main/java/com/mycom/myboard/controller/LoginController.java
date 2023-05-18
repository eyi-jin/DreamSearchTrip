package com.mycom.myboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myboard.dto.UserDto;
import com.mycom.myboard.service.LoginService;

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
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@PostMapping(value="/login")
	public Map<String, String> login(@RequestBody UserDto dto, HttpSession session) {
		UserDto userDto = loginService.login(dto);
		
		Map<String, String> map = new HashMap<>();
		if(userDto != null) {
			session.setAttribute("userDto", userDto); // 서버를 위해 저장
			map.put("userEmail", userDto.getUserEmail());
			map.put("userName", userDto.getUserName());
			map.put("userProfileImageUrl", userDto.getUserProfileImageUrl());
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
}
