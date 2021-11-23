package com.ssafy.ebangjbang.model.service.free;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ebangjbang.model.Dto.FreeDto;
import com.ssafy.ebangjbang.model.mapper.FreeMapper;

@Service
public class FreeServiceImpl implements FreeService{

	@Autowired
	private FreeMapper mapper;
	
	@Override
	public List<FreeDto> getFreeList() throws Exception {
		return mapper.selectFreeList();
	}

	@Override
	public void updateHit(String no) throws Exception {
		mapper.updateHit(no);
	}

	@Override
	public FreeDto getFree(String no) throws Exception {
		return mapper.selectFree(no);
	}

	@Override
	public int registFree(FreeDto free) throws Exception {
		return mapper.insertFree(free);
	}

	@Override
	public int resignFree(String no) throws Exception {
		return mapper.deleteFree(no);
	}

	@Override
	public int modifyFree(FreeDto free) throws Exception {
		return mapper.updateFree(free);
	}

	@Override
	public List<FreeDto> searchFreeList(Map<String, String> map) throws Exception {
		return mapper.selectFreeList(map);
	}

}
