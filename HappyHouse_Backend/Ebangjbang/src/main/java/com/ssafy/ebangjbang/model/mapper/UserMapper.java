package com.ssafy.ebangjbang.model.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ebangjbang.model.Dto.UserDto;

@Mapper
public interface UserMapper {
	public UserDto login(UserDto userDto) throws SQLException;
	public UserDto userInfo(String userid) throws SQLException;
	public int insertUser(UserDto userDto) throws SQLException;
}
