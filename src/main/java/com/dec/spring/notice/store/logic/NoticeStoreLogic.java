package com.dec.spring.notice.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
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

	@Override
	public List<NoticeVO> selectList(SqlSession session, int currentPage) {
		int limit = 10;
		// 1page: offset -> 0
		// 2page: offset -> 10
		// 3page: offset -> 20
		int offset = (currentPage-1)*10;
		RowBounds rowbound = new RowBounds(offset, limit);
		
		return session.selectList("NoticeMapper.selectList", null, rowbound);
	}

	@Override
	public int getTotalCount(SqlSession session) {
		return session.selectOne("NoticeMapper.getTotalCount");
	}

	@Override
	public NoticeVO selectOneByNo(SqlSession session, int noticeNo) {
		return session.selectOne("NoticeMapper.selectOneByNo", noticeNo);
	}

	@Override
	public int updateNotice(SqlSession session, NoticeVO notice) {
		return session.update("NoticeMapper.updateNotice",notice);
	}

	@Override
	public int deleteNotice(SqlSession session, int noticeNo) {
		return session.update("NoticeMapper.deleteNotice", noticeNo);
	}

	@Override
	public List<NoticeVO> searchListByKeyword(SqlSession session, Map<String, String> paramMap, int currentPage) {
		int limit = 10;
		int offset = (currentPage-1)*limit;
		RowBounds rowBound = new RowBounds(offset, limit);
		return session.selectList("NoticeMapper.searchListByKeyword",paramMap, rowBound);
	}

	@Override
	public int getSearchCount(SqlSession session, Map<String, String> paramMap) {
		return session.selectOne("NoticeMapper.getSearchCount",paramMap);
	}
	
}
