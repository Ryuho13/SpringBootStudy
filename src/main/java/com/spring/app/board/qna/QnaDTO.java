package com.spring.app.board.qna;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaDTO {
    private Long boardNum;
    private String boardTitle;
    private String boardWriter;
    private String boardContents;
    private Timestamp boardDate;
    private Long boardHit;
    private Long boardRef;
    private Long boardStep;
    private Long boardDepth;
}
