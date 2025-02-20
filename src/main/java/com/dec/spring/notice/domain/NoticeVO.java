package com.dec.spring.notice.domain;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {
	private int noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeWriter;
	private String noticeFilename;
	private String noticeFileRename;
	private String noticeFilepath;
	private Timestamp noticeDate;
	private Timestamp updateDate;
	private int noticeCount;
	private String noticeYn;
	private MultipartFile uploadFile;
	
	public NoticeVO(String noticeSubject, String noticeContent, String noticeWriter) {
		super();
		this.noticeSubject = noticeSubject;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
	}
	
	public NoticeVO(String noticeSubject, String noticeContent, String noticeWriter, String noticeFilename,
			String noticeFileRename, String noticeFilepath) {
		super();
		this.noticeSubject = noticeSubject;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.noticeFilename = noticeFilename;
		this.noticeFileRename = noticeFileRename;
		this.noticeFilepath = noticeFilepath;
	}

	public int getNoticeNo() {
		return noticeNo;
	}
	public String getNoticeSubject() {
		return noticeSubject;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public String getNoticeFilename() {
		return noticeFilename;
	}
	public String getNoticeFileRename() {
		return noticeFileRename;
	}
	public String getNoticeFilepath() {
		return noticeFilepath;
	}
	public Timestamp getNoticeDate() {
		return noticeDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public int getNoticeCount() {
		return noticeCount;
	}
	public String getNoticeYn() {
		return noticeYn;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	@Override
	public String toString() {
		return "NoticeVO [noticeNo=" + noticeNo + ", noticeSubject=" + noticeSubject + ", noticeContent="
				+ noticeContent + ", noticeWriter=" + noticeWriter + ", noticeFilename=" + noticeFilename
				+ ", noticeFileRename=" + noticeFileRename + ", noticeFilepath=" + noticeFilepath + ", noticeDate="
				+ noticeDate + ", updateDate=" + updateDate + ", noticeCount=" + noticeCount + ", noticeYn=" + noticeYn
				+ ", uploadFile=" + uploadFile + "]";
	}
	
}
