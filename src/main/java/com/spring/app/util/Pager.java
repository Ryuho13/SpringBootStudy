package com.spring.app.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {

    private Long page;
    private Long startNum;
    // 한페이지당 보여줄 글의 갯수
    private Long perPage;
    // 한블럭당 출력할 번호의 갯수
    private Long perBlock;

    // 페이지 블록의 시작번호
    private Long startBlock;
    // 페이지 블록의 끝번호
    private Long endBlock;
    
    // 이전, 다음 블록 존재 여부
    private boolean prev;
    private boolean next;

    public Long getPerPage() {
        if (this.perPage == null || this.perPage < 1) {
            this.perPage = 10L;
        }
        return this.perPage;
    }

    public Long getPerBlock() {
        if (this.perBlock == null || this.perBlock < 1) {
            this.perBlock = 5L;
        }
        return this.perBlock;
    }

    public Long getPage() {
        if (this.page == null || this.page < 1) {
            this.page = 1L;
        }
        return this.page;
    }

    // 페이지네이션 계산 메서드
    public void doPaging(Long totalCount) {
        // 1. DB에서 사용할 startNum 계산
        this.startNum = (this.getPage() - 1) * this.getPerPage();

        // 2. 전체 페이지 수 구하기
        Long totalPage = totalCount / this.getPerPage();
        if (totalCount % this.getPerPage() != 0) {
            totalPage++;
        }
        // page값이 totalpage의 값의 범위를 벗어 난 경우(보다 큰경우)
        if(this.getPage()>totalPage) {
        	this.page=totalPage;
        }
        
        // 없는 페이지를 요청했을 경우, 마지막 페이지로 강제 이동
        if(this.getPage() > totalPage && totalPage > 0) {
            this.setPage(totalPage);
        }

        // 3. 총 블럭 수 구하기
        Long totalBlock = totalPage / this.getPerBlock();
        if (totalPage % this.getPerBlock() != 0) {
            totalBlock++;
        }

        // 4. 페이지 번호로 현재 블럭 번호 구하기
        Long curBlock = this.getPage() / this.getPerBlock();
        if (this.getPage() % this.getPerBlock() != 0) {
            curBlock++;
        }

        // 5. 현재 블럭 번호로 시작 번호와 끝 번호 구하기
        this.startBlock = (curBlock - 1) * this.getPerBlock() + 1;
        this.endBlock = curBlock * this.getPerBlock();

        // 6. 현재 블럭이 마지막 블럭이라면 끝 번호를 총 페이지 수로 대입
        if (curBlock == totalBlock) {
            this.endBlock = totalPage;
        }

        // 7. 이전, 다음 블럭 존재 여부
        if (curBlock > 1) {
            this.prev = true;
        }
        
        if (curBlock < totalBlock) {
            this.next = true;
        }
    }
}

