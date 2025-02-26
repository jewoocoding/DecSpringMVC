package com.dec.spring.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dec.spring.board.domain.BoardVO;
import com.dec.spring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore{

	@Override
	public List<BoardVO> selectBoardList(SqlSession session, int currentPage) {
		int limit = 10;
		int offset = (currentPage-1)*limit;
		RowBounds rowbound = new RowBounds(offset, limit);
		return session.selectList("BoardMapper.selectBoardList", null, rowbound);
	}

	@Override
	public int getTotalCount(SqlSession session) {
		return session.selectOne("BoardMapper.getTotalCount");
	}

	
}
