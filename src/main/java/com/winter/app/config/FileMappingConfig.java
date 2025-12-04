package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.files.FileManager;

@Configuration
public class FileMappingConfig implements WebMvcConfigurer{

	@Value("${app.upload.profile}")
	private String uploadPath; 
	
	@Value("${app.upload.url}")
	private String urlPath; // /file/**

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(urlPath) // urlPath로 오면
		.addResourceLocations(uploadPath);  // uploadPath 에서 찾을 것
		
	}
	
	
	
	
//	@Bean
//	FileManager getFileManager() {
//		return new FileManager();
//	}
	
}
