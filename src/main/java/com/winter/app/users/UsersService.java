package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManager;

@Service
public class UsersService {
	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private FileManager fileManager;

	@Value("${app.upload.profile}")
	private String profileUploadPath;
	
	
	public int register(UsersDTO usersDTO, MultipartFile profile) throws Exception {
		// WARNING: Storing plain-text passwords is a major security vulnerability.
        // Spring Security with a PasswordEncoder should be used here.
		int result = usersDAO.register(usersDTO);

		if (profile != null && !profile.isEmpty()) {
            File file = new File(profileUploadPath);
            String fileName = fileManager.fileSave(file, profile);
            UsersFileDTO userFileDTO = new UsersFileDTO();
            userFileDTO.setUsername(usersDTO.getUsername());
            userFileDTO.setFileName(fileName);
            userFileDTO.setFileOrigin(profile.getOriginalFilename());
            usersDAO.addProfile(userFileDTO);
        }

		return result;
	}
	
	public UsersDTO login(UsersDTO usersDTO) throws Exception {
		// 1. DAO로 username으로 user 정보 조회
		UsersDTO resultDTO = usersDAO.mypage(usersDTO);

		// 2. user 정보가 없으면 null 리턴
		if(resultDTO == null) {
			return null;
		}

		// 3. user 정보가 있으면 password 비교
		// 	- password가 일치하면 user 정보 리턴
		//  - password가 불일치하면 null 리턴
		if(usersDTO.getPassword().equals(resultDTO.getPassword())) {
			return resultDTO;
		} else {
			return null;
		}
	}

	public UsersDTO mypage(UsersDTO usersDTO)throws Exception{
		return usersDAO.mypage(usersDTO);
	}
	
	
}
