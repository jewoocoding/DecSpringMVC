package com.dec.spring.notice.store;

import org.apache.ibatis.session.SqlSession;

import com.dec.spring.notice.domain.NoticeVO;

public interface NoticeStore {
	
	int insertNotice(SqlSession session, NoticeVO notice);
}
