package com.ssafy.ebangjbang.model.service.admin;

import java.util.List;

import com.ssafy.ebangjbang.model.Dto.FreeDto;
import com.ssafy.ebangjbang.model.Dto.UserDto;

public interface AdminService {
	List<UserDto> getUserList() throws Exception;
	
	List<FreeDto> getFreeList() throws Exception;

	boolean deleteUser(String id) throws Exception;
}
