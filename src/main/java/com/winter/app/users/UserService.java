package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManager;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FileManager fileManager;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${app.upload.user}")
	private String uploadPath;
	
	public boolean getError(UserDTO userDTO, BindingResult bindingResult)throws Exception{
		//check : true  -> 검증 실패, error 존재
		//check : flase -> 검증 성공, error 존재 X
		//1. annotation 검증 결과
		boolean check = bindingResult.hasErrors();
		
		//2. password 일치 하는지 검증
		if(!userDTO.getPassword().equals(userDTO.getPasswordCheck())) {
			check=true;
			//bindingResult.rejectValue("멤버변수명", "properties의 키");
			bindingResult.rejectValue("passwordCheck", "user.password.equal");
		}
		
		//3. ID 중복 체크
		//ID는 가입시에만 검증
		if(userDTO.getUsername() != null && bindingResult.getTarget().getClass().getSimpleName().equals("UserDTO")) {
			UserDTO checkDTO = userDAO.mypage(userDTO);
			if(checkDTO != null) {
				check=true;
				bindingResult.rejectValue("username", "user.username.duplication");
			}
		}

		//4. Email 중복 체크
		if(userDTO.getEmail() != null && bindingResult.getTarget().getClass().getSimpleName().equals("UserDTO")) {
			UserDTO checkDTO = userDAO.findByEmail(userDTO);
			if(checkDTO != null) {
				check=true;
				bindingResult.rejectValue("email", "user.email.duplication");
			}
		}
		
		return check;
	}
	
	public int register(UserDTO userDTO, MultipartFile profile)throws Exception{
		// 1. password를 암호화
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		// 2. user 정보 저장
		int result = userDAO.register(userDTO);
		
		if(profile == null || profile.isEmpty()) {
			return result;
		}
		
		File file = new File(uploadPath);
		
		String fileName = fileManager.fileSave(file, profile);
		
		UserFileDTO userFileDTO = new UserFileDTO();
		userFileDTO.setUsername(userDTO.getUsername());
		userFileDTO.setFileName(fileName);
		userFileDTO.setFileOrigin(profile.getOriginalFilename());
		
		userDAO.addProfile(userFileDTO);
		
		return result;
	}
	public UserDTO detail(UserDTO userDTO)throws Exception{
		UserDTO loginDTO = userDAO.mypage(userDTO);
		
		if(loginDTO != null) {
			//                           입력된 PW        암호화된 PW
			if(passwordEncoder.matches(userDTO.getPassword(), loginDTO.getPassword())) {
				return loginDTO;
			}else {
				loginDTO = null;
			}
		}
		
		
		return loginDTO;
	}
	
	public int update(UserDTO userDTO)throws Exception{
		return userDAO.update(userDTO);
	}
	
	public UserDTO mypage(UserDTO userDTO) throws Exception {
		return userDAO.mypage(userDTO);
	}

	public boolean checkPassword(String username, String oldPassword) throws Exception {
		UserDTO user = new UserDTO();
		user.setUsername(username);
		UserDTO loginUser = userDAO.mypage(user);
		return passwordEncoder.matches(oldPassword, loginUser.getPassword());
	}

	public int changePassword(UserDTO userDTO) throws Exception {
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return userDAO.updatePassword(userDTO);
	}
	
	
	
}