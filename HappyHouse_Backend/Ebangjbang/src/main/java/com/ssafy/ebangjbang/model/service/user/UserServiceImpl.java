package com.ssafy.ebangjbang.model.service.user;

import java.util.HashMap;
import java.util.Map;

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

	@Override
	public boolean validate(String type, String value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("value", value);
		return mapper.selectUserValidate(map) != 0;
	}

	@Override
	public boolean deleteUser(String id) {
		return mapper.deleteUser(id) == 1;
	}

	@Override
	public boolean modifyUser(UserDto userDto) {
		return mapper.updateUser(userDto) == 1;
	}
	
	
}
