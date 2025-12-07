package com.winter.app.files;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	public boolean fileDelete(File file)throws Exception{
		//1. 어느 경로에 어떤파일명을 지울것인가?
		return file.delete();
	}
	
	//TDD
	public String fileSave(File file, MultipartFile f)throws Exception{
		if(!file.exists()) {
			file.mkdirs();
		}
		

		
		//저장할 파일명 생성 확장자 포함
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+"_"+f.getOriginalFilename();
		
		//HDD에 파일 저장
		file = new File(file, fileName);
		
		//FileCopyUtils.copy(f.getBytes(), file);
		f.transferTo(file);
		
		return fileName;
		
	}

}
