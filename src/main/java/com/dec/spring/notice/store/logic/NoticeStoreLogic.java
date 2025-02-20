package com.dec.spring.notice.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dec.spring.notice.domain.NoticeVO;
import com.dec.spring.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore{

	@Override
	public int insertNotice(SqlSession session, NoticeVO notice) {
		return session.insert("NoticeMapper.insertNotice",notice);
	}

}
