package com.dec.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.dec.spring.notice.domain.NoticeVO;

public interface NoticeService {
	
	int insertNotice(NoticeVO notice);

	List<NoticeVO> selectList(int currentPage);

	int getTotalCount();

	NoticeVO selectOndeByNo(int noticeNo);

	int updateNotice(NoticeVO notice);

	int deleteNotice(int noticeNo);

	List<NoticeVO> searchListByKeyword(Map<String, String> paramMap, int currentPage);

	int getSearchCount(Map<String, String> paramMap);
}
