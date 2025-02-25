package com.dec.spring.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class PageUtil {

	public Map<String, Integer> generatePageInfo(int totalCount, int currentPage) {
		int boardLimit = 10;
		int maxPage = totalCount / boardLimit;
		maxPage = totalCount % boardLimit != 0 ? maxPage + 1: maxPage;
		
		int naviLimit = 5;
		// page: 1 ~ 5, startNavi -> 1, endNavi -> 5
		// page: 6 ~ 10, startNavi -> 6, endNavi -> 10  
		// page: 11 ~ 15, startNavi - > 11, endNavi -> 15
		int startNavi = ((currentPage-1)/naviLimit)*naviLimit+1;
		int endNavi = startNavi + naviLimit -1;
		if(endNavi > maxPage) {
			endNavi = maxPage;
		}
		Map<String,Integer> pageInfo = new HashMap<>();
		pageInfo.put("startNavi", startNavi);
		pageInfo.put("endNavi", endNavi);
		pageInfo.put("maxPage", maxPage);
		return pageInfo;
	}
}
