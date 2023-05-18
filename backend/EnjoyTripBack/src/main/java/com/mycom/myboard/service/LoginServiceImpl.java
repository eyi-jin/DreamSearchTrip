package com.mycom.myboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myboard.dao.LoginDao;
import com.mycom.myboard.dto.UserDto;

@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
	LoginDao loginDao;

	@Override
	public UserDto login(UserDto userDto) {
		UserDto dto = loginDao.login(userDto.getUserEmail());
		if(dto != null && dto.getUserPassword().equals(userDto.getUserPassword())) {
			dto.setUserPassword(null);
			return dto;
		}
		return null;
	}
	

	
	
}
