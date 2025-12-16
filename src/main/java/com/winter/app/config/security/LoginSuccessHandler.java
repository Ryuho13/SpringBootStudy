package com.winter.app.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.winter.app.config.security.jwt.JwtTokenManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	private JwtTokenManager jwtTokenManager;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("로그in 성공");
		String token = jwtTokenManager.makeAccessToken(authentication);
		System.out.println("Token: " + token);
		
		// 1. Cookie에 Access Token 전송
		Cookie cookie = new Cookie("access_token", token);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(600); // 10분
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}

}
