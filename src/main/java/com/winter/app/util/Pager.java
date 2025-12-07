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
		if(totalCount<1) {
			totalCount=1L;
		}
//	b) 총 글의 갯수로 총 페이지 구하기
		
		Long totalPage =  (long)Math.ceil((double)totalCount/this.getPerPage());
		
		
//		Long totalPage = totalCount/this.getPerPage();
//		if(totalCount%this.perPage !=0) {
//			totalPage++;
//		}
		
//   page값이 totalpage의 값의 범위를 벗어 난 경우(보다 큰경우)
		if(this.getPage()>totalPage) {
			this.page=totalPage;
		}
		
//	c) 총 페이지 수로 총 블럭수 구하기
		Long totalBlock = totalPage / this.getPerBlock();
		if(totalPage%this.perBlock != 0) {
			totalBlock++;
		}
//	d) 페이지 번호로 현재 블럭 번호 구하기
		Long curBlock = this.getPage()/this.perBlock;
		if(this.page%this.perBlock != 0) {
			curBlock++;
		}
		
//	e) 현재 블럭 번호로 시작 번화와 끝 번호 구하기
		this.begin = (curBlock-1)*this.perBlock+1;
		this.end = curBlock * this.perBlock;
//	f) 마지막 블럭이라면 끝번호를 총페이지수로 대입하기
		if(curBlock>=totalBlock) {
			this.begin = (totalBlock-1)*this.perBlock+1;
			this.end = totalPage;
			
		}
		
		this.makeStartNum();
	}
	
	//2. DB에서 일정한 갯수만큼 조회하도록 계산
	private void makeStartNum()throws Exception{
		this.startNum=(page-1)*perPage;
	}

}









