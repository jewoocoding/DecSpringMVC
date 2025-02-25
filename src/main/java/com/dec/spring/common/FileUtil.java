package com.dec.spring.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtil {

	
	public Map<String, String> saveFile(HttpSession session, MultipartFile uploadFile) throws IllegalStateException, IOException {
		Map<String, String> result = new HashMap<String, String>();
		String noticeFilename = uploadFile.getOriginalFilename();
		String noticeFileRename = null;
		String noticeFilepath = null;
		// 중복된 파일이름을 다르게 저장하기 위한 FileRename
		// 시간을 이용하기 위한 SimpleDateFormat
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 현재 시간을 내가 원하는 패턴으로 변환
		String transStr = sdf.format(new Date(System.currentTimeMillis())); // 202502200173811
		// 원본 파일의 확장자 가져오기
		String ext = noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
		// 파일이름 변경완료
		noticeFileRename = transStr + "." + ext;
		noticeFilepath = "/resources/nUploadFiles/"+noticeFileRename;
		
		String folderPath = session.getServletContext().getRealPath("/resources/nUploadFiles");
		String savePath = folderPath + "\\" + noticeFileRename;
		
		uploadFile.transferTo(new File(savePath));
		
		result.put("noticeFilename", noticeFilename);
		result.put("noticeFileRename", noticeFileRename);
		result.put("noticeFilepath", noticeFilepath);
		
		return result;
	}
}
