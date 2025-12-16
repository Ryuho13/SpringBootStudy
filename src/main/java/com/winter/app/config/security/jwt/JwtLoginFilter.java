package com.winter.app.config.security.jwt;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	private JwtTokenManager tokenManager;
	
	public JwtLoginFilter(JwtTokenManager tokenManager, AuthenticationManager authenticationManager) {
		this.setFilterProcessesUrl("/users/loginProcess");
		this.tokenManager=tokenManager;
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		System.out.println("login 시도");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		//UserDetailService의 loadUserByusername 메서드를 호출하고
		//패스워드가 일치하는지 판별하고 Authentication객체를 
		//SecurityContextHolder에 넣어줌
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String accesstoken = tokenManager.makeAccessToken(authResult);
		String refreshToken = tokenManager.makeRefreshToken(authResult);
		
		Cookie cookie = new Cookie("access-token", accesstoken);
		cookie.setPath("/");
		cookie.setMaxAge(60);
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
		
		cookie = new Cookie("refresh-token", refreshToken);
		cookie.setPath("/");
		cookie.setMaxAge(60);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		System.out.println(failed.getMessage());
		// 로그인 실패
		String message= "로그인 실패";
		if(failed instanceof AccountExpiredException) {
			message = "계정 유호기간 만료";
		}
		if(failed instanceof LockedException) {
			message = "계정 잠긴";
		}
		if(failed instanceof CredentialsExpiredException) {
			message = "비번 유효 기간 만료";
		}
		if(failed instanceof DisabledException) {
			message = "휴면 계정";
		}
		if(failed instanceof BadCredentialsException) {
			message = "비번 틀림";
		}
		if(failed instanceof InternalAuthenticationServiceException) {
			message = "id 틀림";
		}
		// 추가된 코드: 동시 로그인 시도 시 메시지 처리
		if(failed instanceof SessionAuthenticationException) {
			message = "이미 로그인된 사용자입니다."; 
		}
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("./login?error=true&message="+ message);
		
	}
}