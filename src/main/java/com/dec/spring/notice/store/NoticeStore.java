package com.dec.spring.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.dec.spring.notice.domain.NoticeVO;

public interface NoticeStore {
	
	int insertNotice(SqlSession session, NoticeVO notice);

	List<NoticeVO> selectList(SqlSession session, int currentPage);

	int getTotalCount(SqlSession session);

	NoticeVO selectOneByNo(SqlSession session, int noticeNo);

	int updateNotice(SqlSession session, NoticeVO notice);

	int deleteNotice(SqlSession session, int noticeNo);

	List<NoticeVO> searchListByKeyword(SqlSession session, Map<String, String> paramMap, int currentPage);

	int getSearchCount(SqlSession session, Map<String, String> paramMap);

}
