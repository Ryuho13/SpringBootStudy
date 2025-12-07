package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.files.FileManager;

@Configuration
public class FileMappingConfig implements WebMvcConfigurer {
	
	@Value("${app.upload}")
	private String uploadPath; //file:///upload/
	
	@Value("${app.upload.url}")
	private String urlPath; // /files/**

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		
		registry
		.addResourceHandler(urlPath)
		.addResourceLocations(uploadPath);
		
	}
	
	
	
	
	
//	@Bean("")
//	FileManager getFileManger() {
//		return new FileManager();
//	}
	

}
