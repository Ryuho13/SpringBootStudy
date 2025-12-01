package com.winter.app.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {
	
	private String kind;
	private String search;
	
	private Long page;
	
	private Long startNum;
	
	
	//한페이지당 보여줄 글의 갯수
	private Long perPage;
	
	//한블럭당 출력할 번호의 갯수
	private Long perBlock;
	
	private Long begin;
	private Long end;
	
	public Long getPerBlock() {
		if(this.perBlock==null || this.perBlock<1||this.perBlock%5!=0) {
			this.perBlock=5L;
		}
		
		return this.perBlock;
	}
	
	public Long getPerPage() {
		if(this.perPage==null|| this.perPage<1 || this.perPage%5 !=0) {
			this.perPage=10L;
		}
		return this.perPage;
	}
	
	public Long getPage() {
		if(this.page==null || this.page<1 ) {
			this.page=1L;
		}
		
		return this.page;
	}
	
	//1. 페이징 계산
//	a) 총 글의 갯수
	public void pageing(Long totalCount)throws Exception{		
//	b) 총 글의 갯수로 총 페이지 구하기
		Long totalPage = totalCount/this.getPerPage();
		if(totalCount%this.perPage !=0) {
			totalPage++;
		}
		
		// ✨ MAX 페이지 초과 예외 처리 (추가)
		// 현재 페이지가 totalPage를 초과할 경우 totalPage로 강제 설정하여 범위를 제한합니다.
		if(this.getPage() > totalPage && totalPage > 0) {
		    this.page = totalPage;
		} else if (totalPage == 0) {
		    this.page = 1L;
		}

//	c) 총 페이지 수로 총 블럭수 구하기
		Long totalBlock = totalPage / this.getPerBlock();
		if(totalPage%this.perBlock != 0) {
			totalBlock++;
		}
//	d) 페이지 번호로 현재 블럭 번호 구하기
		// page가 perBlock의 배수일 때 정확한 블록 계산을 위해 (page-1) 사용 (필수)
		Long curBlock = (this.getPage() - 1) / this.perBlock + 1;
		
//	e) 현재 블럭 번호로 시작 번화와 끝 번호 구하기
		// 시작 번호는 현재 블록 번호를 사용합니다.
		this.begin = (curBlock-1)*this.perBlock+1;
		
		// 끝 번호는 현재 블럭 번호 * perBlock 입니다.
		this.end = curBlock * this.perBlock;
		
//	f) 마지막 블럭이라면 끝번호를 총페이지수로 대입하기
		if(curBlock >= totalBlock) {
			// 마지막 블록의 끝 번호를 totalPage로 설정
			this.end = totalPage;
			// this.page = totalPage; // ❌ 이 코드를 제거하여 페이지 통일 오류를 해결합니다.
		}

		// 총 페이지 수가 0일 경우 예외 처리
		if (totalPage == 0) {
		    this.page = 1L;
		    this.begin = 1L;
		    this.end = 1L;
		}
		
		this.makeStartNum();
	}
	
	//2. DB에서 일정한 갯수만큼 조회하도록 계산
	private void makeStartNum()throws Exception{
		this.startNum=(page-1)*perPage;
	}

}