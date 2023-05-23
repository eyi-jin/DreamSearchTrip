package com.mycom.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myboard.dao.FavDao;
import com.mycom.myboard.dto.FavDto;

@Service
public class FavServiceImpl implements FavService {

	@Autowired
	FavDao favDao;

	@Override
	public int favRegister(FavDto favDto) {
		return favDao.favRegister(favDto);

	}

	@Override
	public int favDelete(int favId) {
		return favDao.favDelete(favId);

	}

	@Override
	public List<FavDto> favView(int userSeq) {
		return favDao.favView(userSeq);
	}

}
