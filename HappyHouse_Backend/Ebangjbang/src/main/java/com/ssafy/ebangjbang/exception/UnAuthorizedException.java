package com.ssafy.ebangjbang.exception;

public class UnAuthorizedException extends RuntimeException{
	private static final long serialVersionUID = 4917019666578060080L;
	
	public UnAuthorizedException() {
		super("[Authority Error] 계정 권한이 유효하지 않습니다.");
	}
	
}
