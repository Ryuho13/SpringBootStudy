package com.spring.app.board.qna;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.spring.app.util.Pager;

@Mapper
public interface QnaDAO {

    // list
    public List<QnaDTO> list(Pager pager) throws Exception;
    
    // count
    public Long count(Pager pager) throws Exception;
    
    // detail
    public QnaDTO detail(QnaDTO qnaDTO) throws Exception;
    
    // add
    public int add(QnaDTO qnaDTO) throws Exception;
    
    // set ref
    public int setRef(QnaDTO qnaDTO) throws Exception;
    
    // update step
    public int updateStep(QnaDTO qnaDTO) throws Exception;
    
    // reply
    public int reply(QnaDTO qnaDTO) throws Exception;
    
    // update
    public int update(QnaDTO qnaDTO) throws Exception;
    
    // delete
    public int delete(QnaDTO qnaDTO) throws Exception;

}
