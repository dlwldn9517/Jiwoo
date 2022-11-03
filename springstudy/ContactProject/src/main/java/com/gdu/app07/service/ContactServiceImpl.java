package com.gdu.app07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app07.domain.ContactDTO;
import com.gdu.app07.repository.ContactDAO;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactDAO dao;

	@Override
	public List<ContactDTO> listAllBoards() {
		return dao.selectAllBoards();
	}

	@Override
	public ContactDTO listBoardByNO(int no) {
		return dao.selectBoardByNo(no);
	}

	@Override
	public int saveBoard(ContactDTO contact) {
		return dao.insertBoard(contact);
	}

	@Override
	public int modifyBoard(ContactDTO contact) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBoard(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
