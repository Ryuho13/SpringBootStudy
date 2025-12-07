package com.winter.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.filters.UserFilter;

import jakarta.servlet.Filter;

//@Configuration
public class UserFilterMappingConfig implements WebMvcConfigurer {
	
	@Bean
	FilterRegistrationBean<Filter> filterRegistrationBean(){
		FilterRegistrationBean<Filter> f = new FilterRegistrationBean<>();
		f.setFilter(new UserFilter());
		f.addUrlPatterns("/qna/*", "/notice/add");
		
		return f;
	}

}
