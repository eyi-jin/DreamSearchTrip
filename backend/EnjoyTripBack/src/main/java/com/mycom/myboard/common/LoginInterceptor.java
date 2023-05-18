package com.mycom.myboard.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycom.myboard.dto.UserDto;


@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	private final String jsonStr = "{\"result\":\"login\"}";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception{
		System.out.println("LoginInterceptor >>>> " + request.getRequestURI());
		System.out.println(handler);
	
		// 프론트엔드 개발 시 CORS 환경일 경우 ( simple request 가 아닌 PUT, DELETE 의 preflight 요청 대비
		if(request.getMethod().contentEquals("OPTIONS")) return true;
		
//		String async = request.getHeader("async");
		// 1# 무조건 통과
		
//		return true;
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		
		if(userDto == null) {
			// 비동기 요청 여부 처리
			response.getWriter().write(jsonStr); // json response
			return false;
		}
		return true;		
		
	}
}
