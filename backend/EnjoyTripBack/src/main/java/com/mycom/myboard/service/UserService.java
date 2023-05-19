package com.mycom.myboard.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myboard.dto.UserDto;

public interface UserService {
	
	int userRegister(UserDto userDto);
	int userPasswordChange(UserDto userDto);
	int userDelete(String userEmail);
}
