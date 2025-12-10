package com.winter.app.config.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException; // 추가된 코드
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	AuthenticationException exception) throws IOException, ServletException {

		// System.out.println("Login Fail");
		// log.info("{}",exception.getMessage());
		// log.info("{}",exception.getClass());
		
		String message= "로그인 실패";
		if(exception instanceof AccountExpiredException) {
			message = "계정 유호기간 만료";
		}
		if(exception instanceof LockedException) {
			message = "계정 잠긴";
		}
		if(exception instanceof CredentialsExpiredException) {
			message = "비번 유효 기간 만료";
		}
		if(exception instanceof DisabledException) {
			message = "휴면 계정";
		}
		if(exception instanceof BadCredentialsException) {
			message = "비번 틀림";
		}
		if(exception instanceof InternalAuthenticationServiceException) {
			message = "id 틀림";
		}
		// 추가된 코드: 동시 로그인 시도 시 메시지 처리
		if(exception instanceof SessionAuthenticationException) {
			message = "이미 로그인된 사용자입니다."; 
		}
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("./login?error=true&message="+ message);
			
	}

}
