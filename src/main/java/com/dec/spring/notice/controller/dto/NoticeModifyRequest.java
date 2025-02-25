package com.dec.spring.notice.controller.dto;

public class NoticeModifyRequest {
	
	private int noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeFilename;
	private String noticeFileRename;
	private String noticeFilepath;
	
	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeSubject() {
		return noticeSubject;
	}

	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeFilename() {
		return noticeFilename;
	}

	public void setNoticeFilename(String noticeFilename) {
		this.noticeFilename = noticeFilename;
	}

	public String getNoticeFileRename() {
		return noticeFileRename;
	}

	public void setNoticeFileRename(String noticeFileRename) {
		this.noticeFileRename = noticeFileRename;
	}

	public String getNoticeFilepath() {
		return noticeFilepath;
	}

	public void setNoticeFilepath(String noticeFilepath) {
		this.noticeFilepath = noticeFilepath;
	}

	@Override
	public String toString() {
		return "NoticeModifyRequest [noticeNo=" + noticeNo + ", noticeSubject=" + noticeSubject + ", noticeContent="
				+ noticeContent + ", noticeFilename=" + noticeFilename + ", noticeFileRename=" + noticeFileRename
				+ ", noticeFilepath=" + noticeFilepath + "]";
	}
	
}
