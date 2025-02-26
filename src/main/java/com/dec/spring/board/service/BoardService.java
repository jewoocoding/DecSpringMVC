package com.dec.spring.board.service;

import java.util.List;

import com.dec.spring.board.domain.BoardVO;

public interface BoardService {

	List<BoardVO> selectBoardList(int currentPage);

	int getTotalCount();

}
