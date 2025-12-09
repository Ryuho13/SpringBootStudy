package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
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
		if(userDTO.getUsername() != null && !bindingResult.hasFieldErrors("username")) {
			UserDTO checkDTO = userDAO.detail(userDTO);
			if(checkDTO != null) {
				check=true;
				bindingResult.rejectValue("username", "user.username.duplication");
			}
		}
		
		return check;
	}
	
	@Transactional
	public int register(UserDTO userDTO, MultipartFile profile)throws Exception{
		// 비밀번호 암호화
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		int result = 0;
		try {
			result = userDAO.register(userDTO);
			log.info("userDAO.register() result: {}", result);
		} catch (Exception e) {
			log.error("Error during userDAO.register()", e);
			// re-throw or handle
			throw e;
		}
		result = userDAO.roleAdd(userDTO);
		if(profile == null || profile.isEmpty()) {
			return result;
		}
		
		File file = new File(uploadPath);
		
		String fileName = fileManager.fileSave(file, profile);
		
		UserFileDTO userFileDTO = new UserFileDTO();
		userFileDTO.setUsername(userDTO.getUsername());
		userFileDTO.setFileName(fileName);
		userFileDTO.setFileOrigin(profile.getOriginalFilename());
		
		try {
			int fileResult = userDAO.userFileAdd(userFileDTO);
			log.info("userDAO.userFileAdd() result: {}", fileResult);
			return result;
		} catch (Exception e) {
			log.error("Error during userDAO.userFileAdd()", e);
			// re-throw or handle
			throw e;
		}
	}
	public UserDTO detail(UserDTO userDTO)throws Exception{
		return userDAO.detail(userDTO);
	}
	
	/*
	//기존 코드
	public int update(UserDTO userDTO)throws Exception{
		return userDAO.update(userDTO);
	}
	*/
	
	// ============== 추가된 코드 ==============
	public int update(UserDTO userDTO)throws Exception{
		return userDAO.update(userDTO);
	}
	
	@Transactional
	public int update(UserDTO userDTO, MultipartFile profile) throws Exception {
	    // 1. 사용자 텍스트 정보 업데이트
	    int result = userDAO.update(userDTO);

	    // 2. 새 프로필 사진이 업로드되었는지 확인
	    if (profile != null && !profile.isEmpty()) {
	        // 2a. 이전 파일명을 얻기 위해 현재 사용자 정보 조회
	        UserDTO currentUser = userDAO.detail(userDTO);
	        
	        // 2b. 새 파일 저장
	        String newFileName = fileManager.fileSave(new File(uploadPath), profile);

	        // 2c. 새 파일 DTO 준비
	        UserFileDTO userFileDTO = new UserFileDTO();
	        userFileDTO.setUsername(userDTO.getUsername());
	        userFileDTO.setFileName(newFileName);
	        userFileDTO.setFileOrigin(profile.getOriginalFilename());

	        // 2d. DB의 파일 정보 업데이트 시도, 실패 시 새로 추가
	        int fileResult = userDAO.updateFile(userFileDTO);
	        if (fileResult == 0) {
	            fileResult = userDAO.userFileAdd(userFileDTO);
	        }

	        // 2e. 기존 파일이 있었고, DB 업데이트가 성공했다면 디스크에서 삭제
	        if (fileResult > 0 && currentUser != null && currentUser.getUserFileDTO() != null && currentUser.getUserFileDTO().getFileName() != null) {
	            // fileManager.fileDelete(new File(uploadPath), currentUser.getUserFileDTO().getFileName()); // 기존 코드
	            File oldFile = new File(uploadPath, currentUser.getUserFileDTO().getFileName());
	            fileManager.fileDelete(oldFile);
	        }
	    }
	    return result;
	}
	// =======================================
	
	
	
}
