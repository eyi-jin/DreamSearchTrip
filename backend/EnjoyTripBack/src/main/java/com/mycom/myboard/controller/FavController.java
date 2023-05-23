package com.mycom.myboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myboard.dto.FavDto;
import com.mycom.myboard.dto.UserDto;
import com.mycom.myboard.service.FavService;

@RestController
public class FavController{

	@Autowired
	FavService favService;
	
	private static final int SUCCESS = 1;
	
	@PostMapping(value="/fav/favRegist")
	protected Map<String, String> favRegist( @RequestBody FavDto favDto, HttpServletRequest request) {
		System.out.println("favDto: "+ favDto);
		Map<String, String>map = new HashMap<>();
		
		favDto.setUserSeq( ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq());
		
		if( favService.favRegister(favDto) == SUCCESS ) {
			map.put("favName", favDto.getFavName());
			map.put("userSeq", String.valueOf(favDto.getUserSeq()));
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
 
	@DeleteMapping(value="/fav/favDelete/{favId}")
	protected Map<String, String> favDelete( @PathVariable int favId ) {
		
		Map<String, String> map = new HashMap<>();
		
		if( favService.favDelete(favId) == SUCCESS ) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}

	@GetMapping(value="/fav/favView")
	protected List<FavDto> favView( HttpServletRequest request ) {
		
		int userSeq = ((UserDto) request.getSession().getAttribute("userDto")).getUserSeq();
		
		return favService.favView(userSeq);
		
	}

}
