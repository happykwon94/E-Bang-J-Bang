package com.ssafy.ebangjbang.model.service.notice;

import java.util.List;

import com.ssafy.ebangjbang.model.notice.NoticeDto;

public interface NoticeService {
	// 전체 공지사항 가져오기
	List<NoticeDto> getNoticeList() throws Exception;
	
	// no에 해당하는 공지사항 가져오기
	void updateHit(String no) throws Exception;
	NoticeDto getNotice(String no) throws Exception;
	
	// 공지사항 등록
	int registNotice(NoticeDto notice) throws Exception;
	
	// 공지사항 삭제
	int resignNotice(String no) throws Exception;
	
	// 공지사항 수정
	int modifyNotice(NoticeDto notice) throws Exception;
	
	// 공지사항 검색
	List<NoticeDto> searchNoticeList(String type, String keyword) throws Exception;
}
