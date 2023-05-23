package com.mycom.myboard.service;

import java.util.List;

import com.mycom.myboard.dto.FavDto;

public interface FavService {
	int favRegister(FavDto favDto);

	int favDelete(int favId);

	List<FavDto> favView(int userSeq);
}
