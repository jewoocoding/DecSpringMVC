package com.dec.spring.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dec.spring.board.domain.BoardVO;
import com.dec.spring.board.service.BoardService;
import com.dec.spring.common.PageUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService bService;
	private PageUtil pageUtil;
	
	@Autowired
	public BoardController(BoardService bService, PageUtil pageUtil) {
		this.bService = bService;
		this.pageUtil = pageUtil;
	}
	
	@GetMapping("/list")
	public String showBoardList(Model model
			,@RequestParam(value="currentPage", defaultValue = "1") int currentPage) {
		try {
			int totalCount = bService.getTotalCount();
			Map<String,Integer> pageInfo = pageUtil.generatePageInfo(totalCount, currentPage);
			List<BoardVO> bList = bService.selectBoardList(currentPage);
			
			model.addAttribute("startNavi",pageInfo.get("startNavi"));
			model.addAttribute("endNavi",pageInfo.get("endNavi"));
			model.addAttribute("maxPage",pageInfo.get("maxPage"));
			model.addAttribute("bList",bList);
			return "board/list";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
}
