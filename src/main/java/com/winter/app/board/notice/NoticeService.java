package com.winter.app.board.notice;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardService; // 인터페이스 임포트
import com.winter.app.util.Pager;

@Service
public class NoticeService implements BoardService {
    
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Value("${app.upload.notice}")
	private String uploadPath;
	
    // 1. LIST
	@Override
	public List<BoardDTO> list (Pager pager)throws Exception{
		// 1. totalCount 구하기
		Long totalCount= noticeDAO.count(pager);
		pager.pageing(totalCount);
		return noticeDAO.list(pager);
	}
	
	@Override
	public BoardDTO detail(BoardDTO boardDTO)throws Exception{
        // DAO에서 조회된 BoardDTO (실제로는 NoticeDTO)를 반환합니다.
		return noticeDAO.detail(boardDTO);
	}
	
	@Override
	public int add(BoardDTO boardDTO, MultipartFile [] attach)throws Exception{
		// 1. 파일을 HDD에 저장
			// 1) 어디에 저장?
			// 2) 어떤 이름으로 저장?
		for (MultipartFile f: attach) {
			File file = new File(uploadPath);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			String fileName = UUID.randomUUID().toString();
			fileName = fileName+"_"+f.getOriginalFilename();
			
			file = new File(file, fileName);
			// 3. 파일 저장
			//
			FileCopyUtils.copy(f.getBytes(), file);
			
		}
		
//		NoticeDTO noticeDTO = (NoticeDTO)boardDTO; 
//		return noticeDAO.add(noticeDTO);
		return 1;
	}
	
	@Override
	public int update(BoardDTO boardDTO)throws Exception{
		NoticeDTO noticeDTO = (NoticeDTO)boardDTO; 
		return noticeDAO.update(noticeDTO);
	}
	
	@Override
	public int delete(BoardDTO boardDTO)throws Exception{
		NoticeDTO noticeDTO = (NoticeDTO)boardDTO;
		return noticeDAO.delete(noticeDTO);
	}

	@Override
	public int add(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}