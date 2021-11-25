package com.ssafy.ebangjbang.model.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ebangjbang.model.Dto.FreeDto;
import com.ssafy.ebangjbang.model.Dto.UserDto;
import com.ssafy.ebangjbang.model.mapper.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper mapper;

	@Override
	public List<UserDto> getUserList() throws Exception {
		return mapper.selectUserList();
	}

	@Override
	public List<FreeDto> getFreeList() throws Exception {
		return mapper.selectFreeList();
	}

	@Override
	public boolean deleteUser(String id) throws Exception {
		return mapper.deleteUser(id) == 1;
	}

}
