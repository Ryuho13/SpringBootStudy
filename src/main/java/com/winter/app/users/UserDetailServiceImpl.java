package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class UserDetailServiceImpl extends DefaultOAuth2UserService implements UserDetailsService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("{}", userRequest);
		return super.loadUser(userRequest);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인 요청");
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		UserDetails userDetails;
		
		 try {
			userDetails = userDAO.detail(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
		 
		return userDetails;
	}
	
}
