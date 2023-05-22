package com.mycom.myboard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.myboard.common.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Autowired
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/", "/index.html")
				.excludePathPatterns("/css/**", "/js/**", "/img/**")
				.excludePathPatterns("/login/**", "/favicon.ico", "/users", "/users/idcheck/**");
		
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	            .allowedOriginPatterns("*")
	            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
	            .allowedHeaders("*")
	            .allowCredentials(true)
	            .maxAge(3000);
	}
	
	
}
