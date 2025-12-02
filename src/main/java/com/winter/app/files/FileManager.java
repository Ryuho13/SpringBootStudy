package com.winter.app.files;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	// TDD
	public String fileSave(File file, MultipartFile f)throws Exception{
		if(!file.exists()) {
			file.mkdirs();
		}
		
		// 저장할 파일명 생성 확장자 포함
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+"_"+f.getOriginalFilename();
		
		// HDD에 파일 저장 
		file = new File(file, fileName);
		// 3. 파일 저장
		//FileCopyUtils.copy(f.getBytes(), file);
		f.transferTo(file);
		
		return fileName;
	}
	
}
