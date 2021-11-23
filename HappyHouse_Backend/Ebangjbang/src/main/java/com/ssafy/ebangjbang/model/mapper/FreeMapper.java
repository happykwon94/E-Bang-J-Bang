package com.ssafy.ebangjbang.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ebangjbang.model.Dto.FreeDto;

@Mapper
public interface FreeMapper {
	List<FreeDto> selectFreeList() throws Exception;
	int insertFree(FreeDto free) throws Exception;
	int deleteFree(String no) throws Exception;
	int updateFree(FreeDto free) throws Exception;
	
	List<FreeDto> selectFreeList(Map<String, String> map) throws Exception;
	FreeDto selectFree(String no) throws Exception;
	void updateHit(String no) throws Exception;
}
