package com.gdu.app06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.service.BoardService;

@Controller
public class BoardController {
	
	// Controller는 Service를 사용.
	@Autowired		// 컨테이너에 생성된 bean 중에서 BoardService 타입의 bean을 가져오시오.
	private BoardService boardService;
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("brd/list")
	public String list(Model model) {	// Model은 forward할 속성(Attribute)을 저장할 때 사용.
		model.addAttribute("boards", boardService.findAllBoards());
		return "board/list";	// board 폴더의 list.jsp로 forward
	}
	
	@GetMapping("brd/write")
	public String write() {
		return "board/write";	// board 폴더의 write.jsp로 forward
	}
		
	@PostMapping("brd/add")				// insert, update, delete는 Redirect
	public String add(BoardDTO board) {	
		boardService.saveBoard(board);	// saveBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list";	// 까먹으면 죽음뿐 : Redirect 뒤에는 요청 주소값을 적어야함 (jsp X)
	}
	
	@GetMapping("brd/detail")	// 상세보기는 GET으로 함
	public String detail(@RequestParam(value="board_no", required=false, defaultValue="0") int board_no
					   , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));
		return "board/detail";	// board 폴더의 detail.jsp로 forward
	}

	@PostMapping("brd/edit")
	public String edit(int board_no 
					 , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));
		return "board/edit";	// board 폴더의 edit.jsp로 forward
	}
	
	@PostMapping("brd/modify")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);	// modifyBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/detail?board_no=" + board.getBoard_no();
	}
	
	@PostMapping("brd/remove")
	public String remove(int board_no) {
		boardService.removeBoard(board_no);	  // removeBoard()로부터 0/1이 반환되지만 처리하지 않았다.
		return "redirect:/brd/list";
	}
	
	
	
	
	
	
	
	
	
	
}
