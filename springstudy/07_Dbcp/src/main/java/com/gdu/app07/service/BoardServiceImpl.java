package com.gdu.app07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;


public class BoardServiceImpl implements BoardService {

	
	// Service는 DAO를 사용합니다.
	// @Autowired  // 컨테이너에 생성된 bean 중에서 BoardDAO 타입의 bean을 가져오시오.
	private BoardDAO dao;
	
	
	@Override
	public List<BoardDTO> findAllBoards() {
		return dao.selectAllBoards();
	}

	@Override
	public BoardDTO findBoardByNo(int board_no) {
		return dao.selectBoardByNo(board_no);
	}

	@Override
	public int saveBoard(BoardDTO board) {
		return dao.insertBoard(board);
	}

	@Override
	public int modifyBoard(BoardDTO board) {
		return dao.updateBoard(board);
	}

	@Override
	public int removeBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}

}
