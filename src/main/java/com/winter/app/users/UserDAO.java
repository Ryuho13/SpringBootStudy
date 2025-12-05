package com.winter.app.users;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
	
	public int register(UserDTO usersDTO)throws Exception;
	
	public UserDTO mypage(UserDTO usersDTO)throws Exception;
	
	public int addProfile(UserFileDTO userFileDTO)throws Exception;
	
	public int update(UserDTO usersDTO) throws Exception;
	
	public int deleteProfile(UserFileDTO usersFileDTO) throws Exception;
	
	public int updateProfile(UserFileDTO usersFileDTO) throws Exception;
	
	public int updatePassword(UserDTO usersDTO) throws Exception;

	public UserDTO findByEmail(UserDTO userDTO) throws Exception;
}
