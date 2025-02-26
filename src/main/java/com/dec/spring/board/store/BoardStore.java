package com.dec.spring.board.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dec.spring.board.domain.BoardVO;

public interface BoardStore {

	List<BoardVO> selectBoardList(SqlSession session, int currentPage);

	int getTotalCount(SqlSession session);

}
