package com.spring.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.util.Pager;

@Service
public class QnaService {

    @Autowired
    private QnaDAO qnaDAO;

    public List<QnaDTO> list(Pager pager) throws Exception {
        Long totalCount = qnaDAO.count(pager);
        pager.pageing(totalCount);
        return qnaDAO.list(pager);
    }
    
    public QnaDTO detail(QnaDTO qnaDTO) throws Exception {
        return qnaDAO.detail(qnaDTO);
    }

    @Transactional
    public int add(QnaDTO qnaDTO) throws Exception {
        int result = qnaDAO.add(qnaDTO);
        qnaDTO.setBoardRef(qnaDTO.getBoardNum());
        result = qnaDAO.setRef(qnaDTO);
        return result;
    }

    @Transactional
    public int reply(QnaDTO qnaDTO) throws Exception {
        // 부모글의 정보 조회
        QnaDTO parentDTO = qnaDAO.detail(qnaDTO);
        
        // step을 1씩 업데이트
        qnaDAO.updateStep(parentDTO);

        // 답글의 정보 설정
        qnaDTO.setBoardRef(parentDTO.getBoardRef());
        qnaDTO.setBoardStep(parentDTO.getBoardStep() + 1);
        qnaDTO.setBoardDepth(parentDTO.getBoardDepth() + 1);

        return qnaDAO.reply(qnaDTO);
    }
    
    public int update(QnaDTO qnaDTO) throws Exception {
        return qnaDAO.update(qnaDTO);
    }

    public int delete(QnaDTO qnaDTO) throws Exception {
        return qnaDAO.delete(qnaDTO);
    }
}
