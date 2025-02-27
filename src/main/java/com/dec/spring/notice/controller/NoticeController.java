package com.dec.spring.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dec.spring.common.FileUtil;
import com.dec.spring.common.PageUtil;
import com.dec.spring.notice.controller.dto.NoticeAddRequest;
import com.dec.spring.notice.controller.dto.NoticeModifyRequest;
import com.dec.spring.notice.domain.NoticeVO;
import com.dec.spring.notice.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeService nService;
	private FileUtil fileUtil;
	private PageUtil pageUtil;
	
	// 생성자를 통해 의존성 주입
	@Autowired
	public NoticeController(NoticeService nService, FileUtil fileUtil, PageUtil pageUtil) {
		this.nService = nService;
		this.fileUtil = fileUtil;
		this.pageUtil = pageUtil;
	}
	
//	@RequestMapping(value="/insert", method=RequestMethod.GET)
	@GetMapping("/insert")
	public String showNoticeForm() {
		return "notice/insert";
	}
	
//	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@PostMapping("/insert")
	public String noticeInsert(@ModelAttribute NoticeAddRequest notice,
								@RequestParam("uploadFile") MultipartFile uploadFile,
								HttpSession session, Model model) {
		
		try {
			String noticeWriter = session.getAttribute("memberId") != null 
					? (String)session.getAttribute("memberId") : "anonymous";
			String noticeFilename = null;
			String noticeFileRename = null;
			String noticeFilepath = null;
			Map<String,String> fileInfo = null;

			if(uploadFile != null && !uploadFile.getOriginalFilename().isBlank()) {
				fileInfo = fileUtil.saveFile(session, uploadFile,"notice");
				noticeFilename = fileInfo.get("nFilename");
				noticeFileRename = fileInfo.get("nFileRename");
				noticeFilepath = fileInfo.get("nFilepath");
				
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilepath(noticeFilepath);
			}

			notice.setNoticeWriter(noticeWriter);
//			NoticeVO notice = new NoticeVO(noticeSubject, noticeContent, noticeWriter
//					, noticeFilename, noticeFileRename, noticeFilepath);
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
			Map<String,Integer> pageInfo = pageUtil.generatePageInfo(totalCount, currentPage);
			
			if(nList != null) {
				model.addAttribute("maxPage",pageInfo.get("maxPage"));
				model.addAttribute("startNavi",pageInfo.get("startNavi"));
				model.addAttribute("endNavi",pageInfo.get("endNavi"));
				
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
	public String updateNotice(@ModelAttribute NoticeModifyRequest notice
			,@RequestParam("reloadFile") MultipartFile reloadFile
			, Model model
			, HttpSession session) {
		try {

			String noticeFilename = null;
			String noticeFileRename = null;
			String noticeFilepath = null;
			if(reloadFile != null && !reloadFile.getOriginalFilename().isBlank()) {
				Map<String,String> fileInfo = fileUtil.saveFile(session, reloadFile,"notice");
				noticeFilename = fileInfo.get("nFilename");
				noticeFileRename = fileInfo.get("nFileRename");
				noticeFilepath = fileInfo.get("nFilepath");
				
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilepath(noticeFilepath);
			}
			
//			NoticeVO notice = new NoticeVO(noticeNo, noticeSubject, noticeContent
//					, noticeFilename, noticeFileRename, noticeFilepath);
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
			Map<String,Integer> pageInfo = pageUtil.generatePageInfo(totalCount, currentPage);
			
			model.addAttribute("startNavi",pageInfo.get("startNavi"));
			model.addAttribute("endNavi",pageInfo.get("endNavi"));
			model.addAttribute("maxPage",pageInfo.get("maxPage"));		
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
