package com.dec.spring.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dec.spring.notice.domain.NoticeVO;
import com.dec.spring.notice.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeService nService;
	
	// 생성자를 통해 의존성 주입
	@Autowired
	public NoticeController(NoticeService nService) {
		this.nService = nService;
	}
	
//	@RequestMapping(value="/insert", method=RequestMethod.GET)
	@GetMapping("/insert")
	public String showNoticeForm() {
		return "notice/insert";
	}
	
//	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@PostMapping("/insert")
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
	
	// @RequestMapping - > defaultValue: 값이 전달이 안 되었을 때 default값을 지정해줄 수 있음.
//	@RequestMapping(value="/list", method=RequestMethod.GET)
	@GetMapping("/list")
	public String showNoticeList(Model model
								,@RequestParam(value="page",defaultValue = "1") int currentPage) {
		
		try {
			List<NoticeVO> nList = nService.selectList(currentPage);
			
			int totalCount = nService.getTotalCount();
			int boardLimit = 10;
			int maxPage = totalCount / boardLimit;
			maxPage = totalCount % boardLimit != 0 ? maxPage + 1: maxPage;
			
			
			int naviLimit = 5;
			// page: 1 ~ 5, startNavi -> 1, endNavi -> 5
			// page: 6 ~ 10, startNavi -> 6, endNavi -> 10  
			// page: 11 ~ 15, startNavi - > 11, endNavi -> 15
			int startNavi = ((currentPage-1)/naviLimit)*naviLimit+1;
			int endNavi = startNavi + naviLimit -1;
			if(endNavi > maxPage) {
				endNavi = maxPage;
			}
			
			if(nList != null) {
				model.addAttribute("maxPage",maxPage);
				model.addAttribute("startNavi",startNavi);
				model.addAttribute("endNavi",endNavi);
				
				// 리스트 출력
				model.addAttribute("nList",nList);
				return "notice/list";
			}else {
				// 오류 페이지
				model.addAttribute("errorMsg","데이터가 존재하지 않습니다.");
				return "common/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
//	@RequestMapping(value="/detail", method=RequestMethod.GET)
	@GetMapping("/detail")
	public String showNoticeDetail(Model model
									, @RequestParam("noticeNo") int noticeNo) {
		
		try {
			NoticeVO notice = nService.selectOndeByNo(noticeNo);
			model.addAttribute("notice",notice);
			return "notice/detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
	}
	
//	@RequestMapping(value="/update", method=RequestMethod.GET)
	@GetMapping("/update")
	public String showModifyForm(@RequestParam("noticeNo") int noticeNo
			, Model model) {
		try {
			NoticeVO notice = nService.selectOndeByNo(noticeNo);
			model.addAttribute("notice",notice);
			return "notice/update";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
	}
	
//	@RequestMapping(value="/update", method=RequestMethod.POST)
	@PostMapping("/update")
	public String updateNotice(@RequestParam("noticeNo") int noticeNo
			,@RequestParam("noticeSubject") String noticeSubject
			,@RequestParam("noticeContent") String noticeContent
			,@RequestParam("reloadFile") MultipartFile reloadFile
			, Model model
			, HttpSession session) {
		try {
			String noticeFilename = reloadFile.getOriginalFilename();
			String noticeFileRename = null;
			String noticeFilepath = null;
			if(noticeFilename != null) {
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
//				String noticeFileRename = sdf.format(new Date(System.currentTimeMillis()));
				String ext = noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
				noticeFileRename = UUID.randomUUID()+"."+ext;
				String folderPath = session.getServletContext().getRealPath("/resources/nUploadFiles");
				String savePath = folderPath + "\\" + noticeFileRename;
				reloadFile.transferTo(new File(savePath)); // 파일저장
				noticeFilepath = "/resources/nUploadFiles/" + noticeFileRename;
			}
			
			NoticeVO notice = new NoticeVO(noticeNo, noticeSubject, noticeContent, noticeFilename, noticeFileRename, noticeFilepath);
			
			int result = nService.updateNotice(notice);
			if(result > 0) {
				return "redirect:/notice/detail?noticeNo="+notice.getNoticeNo();				
			}else {
				model.addAttribute("errorMsg","수정이 완료되지 않았습니다.");
				return "common/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
//	@RequestMapping(value="/delete", method=RequestMethod.GET)
	@GetMapping("/delete")
	public String deleteNotice(Model model
			,@RequestParam("noticeNo") int noticeNo) {
		
		try {
			int result = nService.deleteNotice(noticeNo);
			if(result > 0) {
				return "redirect:/notice/list";				
			}else {
				model.addAttribute("errorMsg","삭제가 완료되지 않았습니다.");
				return "common/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
//	@RequestMapping(value="/search", method=RequestMethod.GET)
	@GetMapping("/search")
	public String showSearchList(Model model
								,@RequestParam("searchCondition") String searchCondition
								,@RequestParam("searchKeyword") String searchKeyword
								,@RequestParam(value="page", defaultValue="1") int currentPage) {
		try {
			// 1. VO 만들기
			// SearchVO search = new SearchVO(searchCondition, searchKeyword);
			// 2. HashMap 사용하기
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("searchCondition", searchCondition);
			paramMap.put("searchKeyword", searchKeyword);
			List<NoticeVO> searchList = nService.searchListByKeyword(paramMap, currentPage);
			
			// pagenation
			int totalCount = nService.getSearchCount(paramMap);
			int borderLimit = 10;
			int naviLimit = 5;
			int startNavi = (currentPage-1)/naviLimit*naviLimit +1;
			int endNavi = startNavi + naviLimit - 1;
			int maxPage = totalCount % borderLimit == 0 ? totalCount/borderLimit : totalCount/borderLimit+1;
			
			if(endNavi > maxPage)
				endNavi = maxPage;
			
			model.addAttribute("startNavi",startNavi);
			model.addAttribute("endNavi",endNavi);
			model.addAttribute("maxPage",maxPage);		
			model.addAttribute("searchList",searchList);
			model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("searchKeyword", searchKeyword);
			
			return "notice/search";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
	}
}
