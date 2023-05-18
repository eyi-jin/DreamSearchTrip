package com.mycom.myboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myboard.dto.BoardDto;
import com.mycom.myboard.dto.BoardParamDto;
import com.mycom.myboard.dto.BoardResultDto;
import com.mycom.myboard.dto.UserDto;
import com.mycom.myboard.service.BoardService;

// ResponseEntity 사용
// 실패인 경우 모두 404 로
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
public class BoardController {

	@Autowired
	BoardService service;
	
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/boards")
	public ResponseEntity<BoardResultDto> boardList(BoardParamDto boardParamDto){
		
		BoardResultDto boardResultDto;

		if( boardParamDto.getSearchWord().isEmpty() ) {
			boardResultDto = service.boardList(boardParamDto);
		}else {
			boardResultDto = service.boardListSearchWord(boardParamDto);
		}
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return ResponseEntity.ok().body(boardResultDto);
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	
	@GetMapping(value="/boards/{boardId}")
	public ResponseEntity<BoardResultDto> boardDetail(@PathVariable int boardId, HttpSession session){

		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setUserSeq( ((UserDto) session.getAttribute("userDto")).getUserSeq());
		boardParamDto.setBoardId(boardId);

		BoardResultDto boardResultDto = service.boardDetail(boardParamDto);
		// 게시글 작성자와 현 사용자가 동일
		if( ((UserDto) session.getAttribute("userDto")).getUserSeq() == boardResultDto.getDto().getUserSeq() ) {
			boardResultDto.getDto().setSameUser(true);
		}				
				
		if( boardResultDto.getResult() == SUCCESS ) {
			return ResponseEntity.ok().body(boardResultDto);
		}else {
			return ResponseEntity.notFound().build();
		}		 
	}
	
	@PostMapping(value="/boards")
	public ResponseEntity<BoardResultDto> boardInsert(
			BoardDto boardDto, 
			MultipartHttpServletRequest request) {
		
		boardDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		BoardResultDto boardResultDto = service.boardInsert(boardDto, request);
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return ResponseEntity.ok().body(boardResultDto);
		}else {
			return ResponseEntity.notFound().build();
		}		 
	}
	
	// PUT + multipart/form-data (X)
	// In RESTful,
	// PUT & DELETE methods are defined to be idempotent
	
	@PostMapping(value="/boards/{boardId}") 
	public ResponseEntity<BoardResultDto> boardUpdate(
			BoardDto boardDto, 
			MultipartHttpServletRequest request){

		boardDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		BoardResultDto boardResultDto = service.boardUpdate(boardDto, request);

		if( boardResultDto.getResult() == SUCCESS ) {
			return ResponseEntity.ok().body(boardResultDto);
		}else {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@DeleteMapping(value="/boards/{boardId}") 
	public ResponseEntity<BoardResultDto> boardDelete(@PathVariable(value="boardId") int boardId){
		BoardResultDto boardResultDto = service.boardDelete(boardId);
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return ResponseEntity.ok().body(boardResultDto);
		}else {
			return ResponseEntity.notFound().build();
		}		 
	}
}
