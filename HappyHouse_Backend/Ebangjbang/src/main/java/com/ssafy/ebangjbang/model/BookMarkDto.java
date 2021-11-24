package com.ssafy.ebangjbang.model;

public class BookMarkDto {
	private String no;
	private String userNo;
	private String housedealNo;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getHousedealNo() {
		return housedealNo;
	}
	public void setHousedealNo(String housedealNo) {
		this.housedealNo = housedealNo;
	}
	@Override
	public String toString() {
		return "BookMarkDto [no=" + no + ", userNo=" + userNo + ", housedealNo=" + housedealNo + "]";
	}
	
}
