package com.mycom.myboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myboard.dao.UserDao;
import com.mycom.myboard.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;

	@Override
	public int userRegister(UserDto userDto) {
		
		return userDao.userRegister(userDto);
	}

	@Override
	public int userPasswordChange(UserDto userDto) {
		return userDao.userPasswordChange(userDto);
	}

	@Override
	public int userDelete(String userEmail) {
		return userDao.userDelete(userEmail);
	}

	@Override
	public int userIdCheck(String userEmail) {
		return userDao.userIdCheck(userEmail);
	}
	
	
}
