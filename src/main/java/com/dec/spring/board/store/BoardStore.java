package com.dec.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dec.spring.board.controller.dto.BoardAddRequest;
import com.dec.spring.board.controller.dto.BoardUpdateRequest;
import com.dec.spring.board.domain.BoardVO;

public interface BoardStore {

	List<BoardVO> selectBoardList(SqlSession session, int currentPage);

	int getTotalCount(SqlSession session);

	int insertBoard(SqlSession session, BoardAddRequest board);

	BoardVO selectOneByNo(SqlSession session, int boardNo);

	int deleteBoard(SqlSession session, int boardNo);

	int updateBoard(SqlSession session, BoardUpdateRequest board);

}
