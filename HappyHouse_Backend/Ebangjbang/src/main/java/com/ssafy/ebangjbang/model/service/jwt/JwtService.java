package com.ssafy.ebangjbang.model.service.jwt;

import java.util.Map;

public interface JwtService {
	<T> String create(T id, T nickName, String subject);
	Map<String, Object> get(String key);
	String getUserId();
	boolean isUsable(String jwt);
}
