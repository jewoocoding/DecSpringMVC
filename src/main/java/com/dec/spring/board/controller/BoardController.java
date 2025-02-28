package com.dec.spring.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dec.spring.board.controller.dto.BoardAddRequest;
import com.dec.spring.board.controller.dto.BoardUpdateRequest;
import com.dec.spring.board.domain.BoardVO;
import com.dec.spring.board.service.BoardService;
import com.dec.spring.common.FileUtil;
import com.dec.spring.common.PageUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService bService;
	private PageUtil pageUtil;
	private FileUtil fileUtil;
	
	@Autowired
	public BoardController(BoardService bService, PageUtil pageUtil, FileUtil fileUtil) {
		this.bService = bService;
		this.pageUtil = pageUtil;
		this.fileUtil = fileUtil;
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
	
	@GetMapping("/insert")
	public String showBoardInsertForm(Model model) {
		try {
			return "board/insert";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
	}
	
	@PostMapping("/insert")
	public String insertBoard(Model model
			,@ModelAttribute BoardAddRequest board
			,@RequestParam("uploadFile") MultipartFile uploadFile
			,HttpSession session) {
		try {
			if(session.getAttribute("memberId") != null) {
				board.setBoardWriter((String)session.getAttribute("memberId"));
			}else {
				model.addAttribute("errorMsg","로그인이 필요합니다.....................");
				return "common/error";
			}
			
			if(uploadFile != null && !uploadFile.getOriginalFilename().isBlank()) {
				Map<String,String> fileInfo = fileUtil.saveFile(session, uploadFile,"board");
				board.setBoardFilename(fileInfo.get("bFilename"));
				board.setBoardFileRename(fileInfo.get("bFileRename"));
				board.setBoardFilepath(fileInfo.get("bFilepath"));				
			}
			int result = bService.insertBoard(board);
			if(result > 0) {
				return "redirect:/board/list";				
			}else {
				model.addAttribute("errorMsg","게시글 등록에 실패하였습니다........");
				return "common/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
	@GetMapping("/detail/{boardNo}")
	public String showBoardDetail(@PathVariable("boardNo") int boardNo
			,Model model) {
		try {
			BoardVO board = bService.selectOneByNo(boardNo);
			model.addAttribute("board",board);
			return "board/detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
		
	}
	
	@GetMapping("/delete/{boardNo}")
	public String deleteBoard(Model model
			,@PathVariable("boardNo") int boardNo) {
		try {
			int result = bService.deleteBoard(boardNo);
			if(result > 0) {
				return "redirect:/board/list";				
			}else {
				model.addAttribute("errorMsg","데이터를 삭제하지 못 했습니다...............");
				return "common/error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
	@GetMapping("/update/{boardNo}")
	public String showBoardUpdateForm(Model model
			,@PathVariable("boardNo") int boardNo) {
		try {
			BoardVO board = bService.selectOneByNo(boardNo);
			model.addAttribute("board",board);
			return "board/modify";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
	@PostMapping("/update")
	public String updateBoard(Model model
			,@ModelAttribute BoardUpdateRequest board
			,@RequestParam("reloadFile") MultipartFile reloadFile
			,HttpSession session) {
		try {
			
			if(session.getAttribute("memberId") == null) {
				model.addAttribute("errorMsg","로그인이 필요합니다!");
				return "common/error";
			}
			String memberId = (String)session.getAttribute("memberId");
			if(!memberId.equals(board.getBoardWriter())) {
				model.addAttribute("errorMsg","자신이 작성한 글만 수정할 수 있습니다...........");
				return "common/error";
			}
			if(reloadFile != null && !reloadFile.getOriginalFilename().isBlank()) {
				Map<String,String> fileInfo = fileUtil.saveFile(session, reloadFile, "board");
				board.setBoardFilename(fileInfo.get("bFilename"));
				board.setBoardFileRename(fileInfo.get("bFileRename"));
				board.setBoardFilepath(fileInfo.get("bFilepath"));						
			}
			
			int result = bService.updateBoard(board);
			if(result > 0) {
				return "redirect:/board/detail/"+board.getBoardNo();
			}else {
				model.addAttribute("errorMsg","수정에 실패했습니다................");
				return "common/error";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg",e.getMessage());
			return "common/error";
		}
	}
	
}
