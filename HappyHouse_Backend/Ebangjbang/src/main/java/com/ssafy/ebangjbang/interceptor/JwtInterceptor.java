package com.ssafy.ebangjbang.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.ebangjbang.exception.UnAuthorizedException;
import com.ssafy.ebangjbang.model.service.jwt.JwtService;

@Component
public class JwtInterceptor implements HandlerInterceptor{

	public static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

	
	// Header에 담겨있는 정보 중 꺼낼 토큰 종류
	private static final String HEADER_AUTH = "auth-token";

	@Autowired
	private JwtService jwtService;

	// 요청이 들어올 때 잡아서 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		final String token = request.getHeader(HEADER_AUTH);

		// 토큰이 존재하고 사용가능한 토큰의 경우
		if(token != null && jwtService.isUsable(token)){
			logger.info("사용 가능 토큰 : {}", token);
			return true;
		}else{
			logger.info("사용 불가능 토큰 : {}", token);
			throw new UnAuthorizedException();
		}

	}
}
