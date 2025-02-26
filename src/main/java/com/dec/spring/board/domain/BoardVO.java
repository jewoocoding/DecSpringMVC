package com.dec.spring.board.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class BoardVO {
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFilename;
	private String boardFileRename;
	private String boardFilepath;
	private int boardCount;
	private Date boardDate;
	private Timestamp updateDate;
	private String boardYn;
	
	public int getBoardNo() {
		return boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public String getBoardFilename() {
		return boardFilename;
	}
	public String getBoardFileRename() {
		return boardFileRename;
	}
	public String getBoardFilepath() {
		return boardFilepath;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public String getBoardYn() {
		return boardYn;
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardFilename=" + boardFilename + ", boardFileRename="
				+ boardFileRename + ", boardFilepath=" + boardFilepath + ", boardCount=" + boardCount + ", boardDate="
				+ boardDate + ", updateDate=" + updateDate + ", boardYn=" + boardYn + "]";
	}
	
}
