package com.ssafy.ebangjbang.model.service.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssafy.ebangjbang.exception.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService{
	
	public static final Logger logger = LoggerFactory.getLogger(JwtServiceImpl.class);
	
	// jwt 토큰을 만들 암호키
	private static final String PRIVATE_KEY = "test";
	private static final int EXPIRE_MINUTES = 60;
	
	/** 
	 * 	PRIVATE_KEY를 가지고 인코딩
	 * */
	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = PRIVATE_KEY.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}

		return key;
	}
	
	@Override
	public <T> String create(T id, T nickName, T auth, String subject) {
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "JWT")	// typ : 토큰의 타입이 JWT임을 지정
				.setHeaderParam("regDate", System.currentTimeMillis()) // 헤더에 담을 정보
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * EXPIRE_MINUTES)) // 토큰 유효기간 설정
				.setSubject(subject)	// 등록된 클레임에 담을 정보
				.claim("id", id)	// 클레임에 기본 등록된 정보 외에 토큰에 담을 정보 세팅
				.claim("nickName", nickName)
				.claim("auth", auth)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())	// 데이터의 위변조 증명
				.compact(); //JWT 키로 생성
		return jwt;
	}

	@Override
	public Map<String, Object> get(String key) {
		// 현재 Request를 받아온다.
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		// request에서 헤더에 담겨있는 JWT 토큰을 가져온다.
		String jwt = request.getHeader("access-token");
		
		Jws<Claims> claims = null;
		try {
			// 토큰을 복호화해서 클레임에 담겨있는 정보를 오픈한다.
			claims = Jwts.parser()
					.setSigningKey(PRIVATE_KEY.getBytes("UTF-8"))
					.parseClaimsJws(jwt);
		} catch (Exception e) {
			logger.error(e.getMessage());
			// 에러를 던진다.
			throw new UnAuthorizedException();
		}
		
		// map 형태로 값을 가져와 반환
		Map<String, Object> value = claims.getBody();
		logger.info("value : {}", value);
		return value;
	}

	@Override
	public String getUserId() {
		// 가져온 정보 map에서 아이디를 가져온다.
		return (String) this.get("user").get("userid");
	}

	@Override
	public boolean isUsable(String jwt) {
		try {
			// 전달받은 jwt 토큰은 복호화해서 파싱 되는지 확인, 파싱되면 유효한 토큰
			Jws<Claims> claims = Jwts.parser().
					setSigningKey(this.generateKey()).parseClaimsJws(jwt);
			return true;
		} catch (Exception e) {
				logger.error(e.getMessage());

			return false;
		}
	}

}
