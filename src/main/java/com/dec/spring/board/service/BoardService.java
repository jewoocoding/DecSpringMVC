package com.dec.spring.board.service;

import java.util.List;

import com.dec.spring.board.controller.dto.BoardAddRequest;
import com.dec.spring.board.domain.BoardVO;

public interface BoardService {

	List<BoardVO> selectBoardList(int currentPage);

	int getTotalCount();

	int insertBoard(BoardAddRequest board);

	BoardVO selectOneByNo(int boardNo);

}
