package com.ssafy.ebangjbang.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ebangjbang.model.Dto.FreeDto;
import com.ssafy.ebangjbang.model.Dto.UserDto;

@Mapper
public interface AdminMapper {
	
	// 유저 정보 가져오기
	List<UserDto> selectUserList() throws Exception;
	
	
	// 자유게시판 글 가져오기
	List<FreeDto> selectFreeList() throws Exception;


	int deleteUser(String id) throws Exception;

}
