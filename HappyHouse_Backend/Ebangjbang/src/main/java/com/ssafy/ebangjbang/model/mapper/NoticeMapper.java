package com.ssafy.ebangjbang.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ebangjbang.model.notice.NoticeDto;

@Mapper
public interface NoticeMapper {
	List<NoticeDto> selectNoticeList() throws Exception;
	int insertNotice(NoticeDto notice) throws Exception;
	int deleteNotice(String no) throws Exception;
	int updateNotice(NoticeDto notice) throws Exception;
	
	List<NoticeDto> selectNoticeList(String type, String keyword) throws Exception;
	NoticeDto selectNotice(String no) throws Exception;
	void updateHit(String no) throws Exception;
}
