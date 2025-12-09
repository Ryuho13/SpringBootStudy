package com.winter.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
	
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		//정적 리소스들을 시큐리티에서 제외
		return web -> web
				.ignoring()
					.requestMatchers("/css/**")
					.requestMatchers("/img/**")
					.requestMatchers("/js/**")
					.requestMatchers("/vendor/**")
					.requestMatchers("/files/**");
	}
	
	// 인증과 인가에 관한 설정
	 @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		http
			.cors((cors)->{cors.disable();})
			.csrf(csrf -> csrf.disable())
			
			// 인가(권한)에 관한 설정
			.authorizeHttpRequests(authz -> authz
					            .requestMatchers("/notice/add","/notice/update", "/notice/delete").hasRole("ADMIN")
					            .requestMatchers("/product/add", "/product/update", "/product/delets").hasAnyRole("MANAGER", "ADMIN")
					            .requestMatchers("/product/**").authenticated()
					            .requestMatchers("/css/**", "/js/**", "/vendor/**", "/img/**", "/files/**").permitAll()
					            .requestMatchers("/notice/list", "/notice/detail", "/qna/list", "/qna/detail").permitAll()
					            .requestMatchers("/users/login", "/users/register").permitAll()
					            .requestMatchers("/", "/WEB-INF/views/**").permitAll()
					            .anyRequest().authenticated()		        )
									
			
			// Login form과 그외 관련 설정
			.formLogin(formLogin -> formLogin
					// 로그인 폼 jsp 경로로 가는 url과 로그인 처리 url 작성
		            .loginPage("/users/login")
		            // .usernameParameter("id") // 만약 파라미터 이름이 다를경우 지정 가능
		            // .passwordParameter("pw") // pw 파라미터 이름 지정
		            .loginProcessingUrl("/users/login")
		            //.defaultSuccessUrl("/", true)
		            .successHandler(loginSuccessHandler)
		            .failureHandler(loginFailHandler)
		            // .failureUrl() // 로그인 실패시 url 지정
		    )
			.logout(logout -> logout
		            .logoutUrl("/users/logout")
		            // .logoutSuccessUrl("/")
		            .addLogoutHandler(this.logout)
		            .logoutSuccessHandler(logoutSucess)
		            .invalidateHttpSession(true)
		            // .deleteCookies("JSESSIONID") ID 세션 지우기  이름은 다를수 있음 - 개발자 도구에서 확인
		            
		        );
			
			return http.build();
	}
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
}