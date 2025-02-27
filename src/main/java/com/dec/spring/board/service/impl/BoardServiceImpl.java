package com.dec.spring.board.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dec.spring.board.controller.dto.BoardAddRequest;
import com.dec.spring.board.domain.BoardVO;
import com.dec.spring.board.service.BoardService;
import com.dec.spring.board.store.BoardStore;

@Service
public class BoardServiceImpl implements BoardService{

	private SqlSession session;
	private BoardStore bStore;
	
	@Autowired
	public BoardServiceImpl(SqlSession session, BoardStore bStore) {
		this.bStore = bStore;
		this.session = session;
	}
	
	@Override
	public List<BoardVO> selectBoardList(int currentPage) {
		return bStore.selectBoardList(session, currentPage);
	}

	@Override
	public int getTotalCount() {
		return bStore.getTotalCount(session);
	}

	@Override
	public int insertBoard(BoardAddRequest board) {
		return bStore.insertBoard(session, board);
	}

	@Override
	public BoardVO selectOneByNo(int boardNo) {
		return bStore.selectOneByNo(session, boardNo);
	}
	
}
