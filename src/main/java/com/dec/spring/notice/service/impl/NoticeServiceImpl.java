package com.dec.spring.notice.service.impl;

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

}
