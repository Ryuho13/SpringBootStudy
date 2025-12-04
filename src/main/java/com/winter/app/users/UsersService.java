package com.winter.app.users;

import java.io.File;
import java.lang.annotation.Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManager;
import com.winter.app.users.UsersDTO.PasswordGroup;

@Service
public class UsersService {
	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private FileManager fileManager;

	@Value("${app.upload.profile}")
	private String profileUploadPath;
	
	

	
	public int register(UsersDTO usersDTO, MultipartFile profile) throws Exception {
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
		UsersDTO resultDTO = usersDAO.mypage(usersDTO);

		if(resultDTO == null) {
			return null;
		}

		if(usersDTO.getPassword().equals(resultDTO.getPassword())) {
			return resultDTO;
		} else {
			return null;
		}
	}

	public UsersDTO mypage(UsersDTO usersDTO)throws Exception{
		return usersDAO.mypage(usersDTO);
	}
	
	public int update(UsersDTO usersDTO, MultipartFile profile) throws Exception {
		int result = usersDAO.update(usersDTO); // Update user basic info

		if (profile != null && !profile.isEmpty()) {
            UsersDTO existingUser = usersDAO.mypage(usersDTO);
            UsersFileDTO currentProfileFile = null;

            if (existingUser != null && existingUser.getFileDTOs() != null && !existingUser.getFileDTOs().isEmpty()) {
                currentProfileFile = existingUser.getFileDTOs().get(0);
                File fileToDelete = new File(profileUploadPath, currentProfileFile.getFileName());
                fileManager.fileDelete(fileToDelete);
                usersDAO.deleteProfile(currentProfileFile); 
            }

            File uploadDirectory = new File(profileUploadPath);
            String fileName = fileManager.fileSave(uploadDirectory, profile);
            
            UsersFileDTO newUserFileDTO = new UsersFileDTO();
            newUserFileDTO.setUsername(usersDTO.getUsername());
            newUserFileDTO.setFileName(fileName);
            newUserFileDTO.setFileOrigin(profile.getOriginalFilename());
            
            usersDAO.addProfile(newUserFileDTO); 
        }
        return result;
	}

    public int updatePassword(UsersDTO usersDTO, BindingResult bindingResult) throws Exception {
        // The old password check and new password equality check are already handled in getError
        if (bindingResult.hasErrors()) {
            return 0; // Indicate failure if getError already found issues
        }
        return usersDAO.updatePassword(usersDTO);
    }
}
