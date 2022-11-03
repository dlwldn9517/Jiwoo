package com.gdu.app07.service;

import java.util.List;

import com.gdu.app07.domain.ContactDTO;

public interface ContactService {
	
	public List<ContactDTO> listAllBoards();
	public ContactDTO listBoardByNO(int no);
	public int saveBoard(ContactDTO contact);
	public int modifyBoard(ContactDTO contact);
	public int removeBoard(int no);

}
