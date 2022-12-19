package com.example.demo.guestbook;

public class GPageVo {
	
	String findStr = "";
	int nowPage = 1;
	int totSize;
	int listSize=10;
	int blockSize=10;
	int totPage;
	int startPage;
	int endPage;
	int startNo; 
	int endNo;
	
	
	public void setData(int nowPage,int totSize) { //페이지 계산
		this.nowPage = nowPage;
		this.totPage = totSize;
		compute();
	}
	
	public void compute() { // 페이징처리 연산 다 여기서 할 것임
		//전체건수를 사용하여 전체 페이지수 계산
		totPage = (int) Math.ceil((double)totSize/listSize); //반환타입 double이라 int로 캐스팅
		endPage = (int) Math.ceil((double)nowPage/blockSize)*blockSize; //nowSize blockSize 둘다 정수형이니 연산위해 둘중하나 double로
		startPage = endPage-blockSize + 1;
		if(endPage>totPage) endPage = totPage;
		
		endNo = nowPage*listSize;
		startNo = endNo-listSize; //mysql에서는 +1을 하지않음. limit기능이 있어서(제로베이스)
		if(endNo>totSize) endNo = totSize;
	}
	
	
	public String getFindStr() {
		return findStr;
	}
	public void setFindStr(String findStr) {
		this.findStr = findStr;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotSize() {
		return totSize;
	}
	public void setTotSize(int totSize) {
		this.totSize = totSize;
		this.compute(); //여기서 페이지 계산 해주자
	}
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public int getBlockSize() {
		return blockSize;
	}
	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}
	public int getTotPage() {
		return totPage;
	}
	public void setTotPage(int totPage) {
		this.totPage = totPage;
		this.compute();
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	

}
