package com.winter.app.users;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersDAO {
	
	public int register(UsersDTO usersDTO)throws Exception;
	
	public UsersDTO mypage(UsersDTO usersDTO)throws Exception;
	
	public int addProfile(UsersFileDTO userFileDTO)throws Exception;
}
