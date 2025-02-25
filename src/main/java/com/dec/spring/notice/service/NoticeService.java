package com.dec.spring.notice.service;

import java.util.List;
import java.util.Map;

import com.dec.spring.notice.controller.dto.NoticeAddRequest;
import com.dec.spring.notice.controller.dto.NoticeModifyRequest;
import com.dec.spring.notice.domain.NoticeVO;

public interface NoticeService {
	
	int insertNotice(NoticeAddRequest notice);

	List<NoticeVO> selectList(int currentPage);

	int getTotalCount();

	NoticeVO selectOndeByNo(int noticeNo);

	int updateNotice(NoticeModifyRequest notice);

	int deleteNotice(int noticeNo);

	List<NoticeVO> searchListByKeyword(Map<String, String> paramMap, int currentPage);

	int getSearchCount(Map<String, String> paramMap);
}
