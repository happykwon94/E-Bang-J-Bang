package com.ssafy.ebangjbang.model.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ebangjbang.model.mapper.NoticeMapper;
import com.ssafy.ebangjbang.model.notice.NoticeDto;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeMapper mapper;

	@Override
	public List<NoticeDto> getNoticeList() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectNoticeList();
	}

	@Override
	public NoticeDto getNotice(String no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectNotice(no);
	}

	@Override
	public int registNotice(NoticeDto notice) throws Exception {
		// TODO Auto-generated method stub
		return mapper.insertNotice(notice);
	}

	@Override
	public int resignNotice(String no) throws Exception {
		// TODO Auto-generated method stub
		return mapper.deleteNotice(no);
	}

	@Override
	public int modifyNotice(NoticeDto notice) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateNotice(notice);
	}

	@Override
	public List<NoticeDto> searchNoticeList(String type, String keyword) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectNoticeList(type, keyword);
	}

	@Override
	public void updateHit(String no) throws Exception {
		mapper.updateHit(no);
	}

}
