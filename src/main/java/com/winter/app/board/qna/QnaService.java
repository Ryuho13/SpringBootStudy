package com.winter.app.board.qna;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardFileDTO;
import com.winter.app.board.BoardService;
import com.winter.app.board.notice.NoticeFileDTO;
import com.winter.app.files.FileManager;
import com.winter.app.util.Pager;

@Service
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.notice}")
	private String uploadPath;

	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		pager.pageing(qnaDAO.count(pager));
		
		
		return qnaDAO.list(pager);
	}

	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.detail(boardDTO);
	}

	@Override
	public int add(BoardDTO boardDTO, MultipartFile [] attach) throws Exception {
		int result = qnaDAO.add(boardDTO);
		qnaDAO.refUpdate(boardDTO);
		
		
		if(attach == null) {
			return result;
		}
		
		//1. 파일을 HDD에 저장
		//   1) 어디에 저장?
		//   2) 어떤 이름으로 저장?
		File file = new File(uploadPath);
		
		for(MultipartFile f: attach) {
			if(f==null || f.isEmpty()) {
				continue;
			}
			String fileName = fileManager.fileSave(file, f);
			//4. 정보를 DB에 저장
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setFileOrigin(f.getOriginalFilename());
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			qnaDAO.fileAdd(boardFileDTO);
		}
		
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		boardDTO = qnaDAO.detail(boardDTO);
		//HDD에서 파일을 삭제
		if(boardDTO.getFileDTOs() != null) {
			for(BoardFileDTO boardFileDTO:boardDTO.getFileDTOs()) {
				File file = new File(uploadPath, boardFileDTO.getFileName());
				boolean flag = fileManager.fileDelete(file);
				
			}
		}
		
		//---------------
		int result = qnaDAO.fileDelete(boardDTO);
		return qnaDAO.delete(boardDTO);
	}
	
	public int reply(QnaDTO qnaDTO)throws Exception{
		//1. 부모의 정보를 조회
		QnaDTO parent=(QnaDTO)qnaDAO.detail(qnaDTO);
		//2. 부모의 정보를 이용해서 step을 업데이트
		int result = qnaDAO.stepUpdate(parent);
		//3. 부모의 정보를 이용해서 ref, step, depth를 세팅
		qnaDTO.setBoardRef(parent.getBoardRef());
		qnaDTO.setBoardStep(parent.getBoardStep()+1);
		qnaDTO.setBoardDepth(parent.getBoardDepth()+1);
		//4. insert
		result=qnaDAO.add(qnaDTO);
		
		return result;
	}
	
	
	@Override
	public BoardFileDTO fileDetail(BoardFileDTO boardFileDTO) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.fileDetail(boardFileDTO);
	}
	

}
