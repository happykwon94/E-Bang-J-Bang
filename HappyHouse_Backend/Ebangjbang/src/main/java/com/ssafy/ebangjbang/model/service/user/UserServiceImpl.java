package com.ssafy.ebangjbang.model.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ebangjbang.model.Dto.UserDto;
import com.ssafy.ebangjbang.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		return mapper.login(userDto);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return mapper.userInfo(userid);
	}

	@Override
	public boolean signUp(UserDto userDto) throws Exception {
		return mapper.insertUser(userDto) == 1;
	}
	
	
}
