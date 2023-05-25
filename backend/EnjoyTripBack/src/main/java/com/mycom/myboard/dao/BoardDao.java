package com.mycom.myboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myboard.dto.BoardDto;
import com.mycom.myboard.dto.BoardFileDto;
import com.mycom.myboard.dto.BoardParamDto;
@Mapper
public interface BoardDao {

	
	// 상세
	BoardDto boardDetail(BoardParamDto boardParamDto);
	List<BoardFileDto> boardDetailFileList(int boardId);
	
	// 게시글을 어떤 사용자가 조회했었는지 확인
	int boardUserReadCount(BoardParamDto boardParamDto); // mapper 에서 map 파라미터
	
	// 신규로 게시글 조회 history 등록
	int boardUserReadInsert(@Param("boardId") int boardId,@Param("userSeq") int userSeq);
	int boardReadCountUpdate(int boardId); // 게시글 조회수 증가
	
	
	////// 삭제
	int boardDelete(int boardId);
	int boardFileDelete(int boardId);
	int boardReadCountDelete(int boardId);
	List<String> boardFileUrlDeleteList(int boardId); // 첨부파일의 물리적인 저장 url 목록
	
	////// 등록
	int boardInsert(BoardDto dto);
	int boardFileInsert(BoardFileDto dto);
	
	////// 목록
	List<BoardDto> boardList(BoardParamDto boardParamDto);
	int boardListTotalCount(); // 총 건수
	
	////// 목록 - 검색이
	List<BoardDto> boardListSearchWord(BoardParamDto boardParamDto);
	int boardListTotalSearchWordCount(BoardParamDto boardParamDto); // 검색어 총 건수
	
	List<BoardDto> boardListSearchWordContent(BoardParamDto boardParamDto);
	int boardListTotalSearchWordContentCount(BoardParamDto boardParamDto); // 검색어 총 건수
	
	List<BoardDto> boardListSearchWordAuthor(BoardParamDto boardParamDto);
	int boardListTotalSearchWordAuthorCount(BoardParamDto boardParamDto); // 검색어 총 건수
	
	////// 내가 쓴글 목록
	List<BoardDto> boardListMy(BoardParamDto boardParamDto);
	int boardListMyTotalCount(BoardParamDto boardParamDto); // 검색어 총 건수
	
	////// 수정
	int boardUpdate(BoardDto dto);
	// 첨부파일은?? 조회수는??
	// 첨부된것들을 단순하게 가져가면 delete insert하는 거고
	// 첨부파일은 건드리지 않는거
	// 복잡하게는 하나하나 ui버튼 추가해주는거
	// 첨부파일은 ?? => 단순 UI 이 경우는 delete - insert, 개별 첨부파일 처리는 복잡 UI ( 파일별 삭제버튼 )
	// 조회수는 ?? => 관련 없음.
	
}
