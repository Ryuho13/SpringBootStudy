package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserDAO userDAO;
	
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
