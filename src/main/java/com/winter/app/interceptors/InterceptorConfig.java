package com.winter.app.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private UserInterceptors userInterceptors;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry
			.addInterceptor(userInterceptors)
			.addPathPatterns("/notice/*")
			.excludePathPatterns("/notice/list","/notice/detail");
		
	}
	
}
