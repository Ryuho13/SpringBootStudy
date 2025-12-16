package com.winter.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.config.security.jwt.JwtAuthenticationFilter;
import com.winter.app.config.security.jwt.JwtLoginFilter;
import com.winter.app.config.security.jwt.JwtTokenManager;
import com.winter.app.users.UserDetailServiceImpl;

import jakarta.websocket.Session;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailHandler loginFailHandler;
	
	@Autowired
	private Logout logout;
	
	@Autowired
	private LogoutSucess logoutSucess;
	
	@Autowired
	private UserDetailServiceImpl detailSerivceImpl;
	
	//----------- JWT 추가 ------------------------------------
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	//정적자원들을 Security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		
		return web -> {
			web
				.ignoring()
					.requestMatchers("/css/**")
					.requestMatchers("/images/**", "/img/**")
					.requestMatchers("/js/**", "/vendor/**")
					;
		};
		
	} 
	
	//인증과 인가에 관한 설정
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		security
			.cors((cors)->{cors.disable();})
			.csrf((csrf)->{csrf.disable();})
			
			//인가(권한)에 관한 설정
			.authorizeHttpRequests((auth)->{
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
					.requestMatchers("/product/add", "/product/update", "/product/delete").hasAnyRole("MANAGER", "ADMIN")
					.requestMatchers("/product/**").authenticated()
					.requestMatchers("/user/mypage", "/user/update", "/user/logout").authenticated()
					.anyRequest().permitAll()
					;
			})
			
			//Login form과 그외 관련 설정
			.formLogin((form)->{
				//front 분리
				form.disable();
						
			})
			
			.logout((logout)->{
				logout
					.logoutUrl("/users/logout")
					//.logoutSuccessUrl("/")
					.addLogoutHandler(this.logout)
					//.logoutSuccessHandler(logoutSucess)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.deleteCookies("remember-me")
					.deleteCookies("access-token", "refresh-token")
					;
			})
			
			.sessionManagement(session ->{
				session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						;
			})
			
			.httpBasic((h)->{
				h.disable();
			})
			
			// 수정된 코드
			.addFilterBefore(new JwtLoginFilter(jwtTokenManager, authenticationConfiguration.getAuthenticationManager()), BasicAuthenticationFilter.class)
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenManager, authenticationConfiguration.getAuthenticationManager()), JwtLoginFilter.class)
			
			// 기존 코드
			//.addFilterAt(new JwtLoginFilter(jwtTokenManager, authenticationConfiguration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class)
			//.addFilterBefore(new JwtAuthenticationFilter(jwtTokenManager, authenticationConfiguration.getAuthenticationManager()), BasicAuthenticationFilter.class)
			
			.oauth2Login(t -> {
				t.userInfoEndpoint((s)->{
					s.userService(detailSerivceImpl);
				});
			})
			
			
			;
	
		return security.build();
	}
	
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}