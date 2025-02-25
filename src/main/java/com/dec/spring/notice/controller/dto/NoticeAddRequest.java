package com.dec.spring.notice.controller.dto;

public class NoticeAddRequest {

	private String noticeSubject;
	private String noticeContent;
	private String noticeWriter;
	private String noticeFilename;
	private String noticeFileRename;
	private String noticeFilepath;

	public NoticeAddRequest() {}

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

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
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
		return "NoticeAddRequest [noticeSubject=" + noticeSubject + ", noticeContent=" + noticeContent
				+ ", noticeWriter=" + noticeWriter + ", noticeFilename=" + noticeFilename + ", noticeFileRename="
				+ noticeFileRename + ", noticeFilepath=" + noticeFilepath + "]";
	}
	
}
