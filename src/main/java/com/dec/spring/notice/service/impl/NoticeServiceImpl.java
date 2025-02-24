package com.dec.spring.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dec.spring.notice.domain.NoticeVO;
import com.dec.spring.notice.service.NoticeService;
import com.dec.spring.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{

	private NoticeStore nStore;
	private SqlSession session;
	
	@Autowired
	public NoticeServiceImpl(NoticeStore nStore, SqlSession session) {
		this.nStore = nStore;
		this.session = session;
	}
	
	@Override
	public int insertNotice(NoticeVO notice) {
		return nStore.insertNotice(session, notice);
	}

	@Override
	public List<NoticeVO> selectList(int currentPage) {
		return nStore.selectList(session, currentPage);
	}

	@Override
	public int getTotalCount() {
		return nStore.getTotalCount(session);
	}

	@Override
	public NoticeVO selectOndeByNo(int noticeNo) {
		return nStore.selectOneByNo(session, noticeNo);
	}

	@Override
	public int updateNotice(NoticeVO notice) {
		return nStore.updateNotice(session, notice);
	}

	@Override
	public int deleteNotice(int noticeNo) {
		return nStore.deleteNotice(session, noticeNo);
	}

	@Override
	public List<NoticeVO> searchListByKeyword(Map<String, String> paramMap, int currentPage) {
		return nStore.searchListByKeyword(session, paramMap, currentPage);
	}

	@Override
	public int getSearchCount(Map<String, String> paramMap) {
		return nStore.getSearchCount(session, paramMap);
	}

}
