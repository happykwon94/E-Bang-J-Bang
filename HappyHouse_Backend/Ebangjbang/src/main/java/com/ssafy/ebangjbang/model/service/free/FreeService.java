package com.ssafy.ebangjbang.model.service.free;

import java.util.List;
import java.util.Map;

import com.ssafy.ebangjbang.model.Dto.FreeDto;

public interface FreeService {

		// 전체 가져오기
		List<FreeDto> getFreeList() throws Exception;
		
		// no에 해당하는 사항 가져오기
		void updateHit(String no) throws Exception;
		FreeDto getFree(String no) throws Exception;
		
		// 등록
		int registFree(FreeDto notice) throws Exception;
		
		// 삭제
		int resignFree(String no) throws Exception;
		
		// 수정
		int modifyFree(FreeDto notice) throws Exception;
		
		// 검색
		List<FreeDto> searchFreeList(Map<String, String> map) throws Exception;

}
