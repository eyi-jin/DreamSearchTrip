package com.mycom.myboard.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myboard.dto.BoardDto;
import com.mycom.myboard.dto.BoardParamDto;
import com.mycom.myboard.dto.BoardResultDto;

public interface BoardService {
	
	BoardResultDto boardDetail(BoardParamDto boardParamDto);
	BoardResultDto boardDelete(int boardId);
	BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);
	BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);
	
	BoardResultDto boardList(BoardParamDto boardParamDto);
	BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
	
	BoardResultDto boardListMy(BoardParamDto boardParamDto);
	
	
}
