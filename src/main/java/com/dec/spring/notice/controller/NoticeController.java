package com.dec.spring.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dec.spring.notice.domain.NoticeVO;
import com.dec.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	private NoticeService nService;
	
	// 생성자를 통해 의존성 주입
	@Autowired
	public NoticeController(NoticeService nService) {
		this.nService = nService;
	}
	
	@RequestMapping(value="/notice/insert", method=RequestMethod.GET)
	public String showNoticeForm() {
		return "notice/insert";
	}
	
	@RequestMapping(value="/notice/insert", method=RequestMethod.POST)
	public String noticeInsert(@RequestParam("noticeSubject") String noticeSubject,
								@RequestParam("noticeContent") String noticeContent,
								@RequestParam("uploadFile") MultipartFile uploadFile,
								HttpSession session, Model model) {
		
		try {
			String noticeWriter = session.getAttribute("memberId") != null 
					? (String)session.getAttribute("memberId") : "anonymous";
			String noticeFilename = uploadFile.getOriginalFilename();
			String noticeFileRename = null;
			String noticeFilepath = null;
			if(noticeFilename != null) {
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
			}
			
			NoticeVO notice = new NoticeVO(noticeSubject, noticeContent, noticeWriter, noticeFilename, noticeFileRename, noticeFilepath);
			
			int result = nService.insertNotice(notice);

			if(result > 0) {
				// 성공
				return "redirect:/notice/list";
			}else {
				// 실패
				model.addAttribute("errorMsg","공지사항 등록이 완료되지 않았습니다.");
				return "common/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
		
		
	}
}
