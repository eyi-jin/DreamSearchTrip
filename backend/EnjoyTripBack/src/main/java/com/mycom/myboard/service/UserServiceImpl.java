package com.mycom.myboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
}
