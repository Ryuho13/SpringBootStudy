package com.winter.app.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Logout implements LogoutHandler{

	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}") 
	private String adminKey;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		System.out.println("LogoutHandler");
		
		WebClient webClient = WebClient.create();
		
		webClient
		.post()
		.uri("https://kapi.kakao.com/v1/user/logout")
		.header("Authorization","KakaoAK " + adminKey)
		.header("Content-Type", "Content-Type: application/x-www-form-urlencoded;charset=utf-8")
		.body(BodyInserters.fromFormData("target_id_type", "target_id").with("id", authentication.getName()))
		.retrieve()
		.bodyToMono(String.class)
		.doOnError(e -> System.err.println("Kakao logout API call failed: " + e.getMessage())) // 에러 로깅 추가
		.subscribe(); // WebClient 호출 실행을 위해 subscribe() 추가
	}

}
