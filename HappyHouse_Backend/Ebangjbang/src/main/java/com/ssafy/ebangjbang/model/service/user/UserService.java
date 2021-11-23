package com.ssafy.ebangjbang.model.service.user;

import com.ssafy.ebangjbang.model.Dto.UserDto;

public interface UserService {

	public UserDto login(UserDto userDto) throws Exception;
	public UserDto userInfo(String userid) throws Exception;
	
	public boolean signUp(UserDto userDto) throws Exception;
	public boolean validate(String type, String value);
}
