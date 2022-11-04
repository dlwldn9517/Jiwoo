package com.gdu.cont01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.cont01.domain.ContactDTO;
import com.gdu.cont01.repository.ContactDAO;

@Service
public class ContactServiceImpl implements ContactService {

	// Service는 DAO를 사용
		@Autowired	// 컨테이너에 생성된 bean 중에서 BoardDAO 타입의 bean을 가져오시오.
		private ContactDAO dao;
	
	@Override
	public List<ContactDTO> findAllContacts() {
		return dao.selectAllContacts();
	}

	@Override
	public ContactDTO findContactByNo(int no) {
		return dao.selectContactByNo(no);
	}

	@Override
	public int register(ContactDTO contact) {
		return dao.insertContact(contact);
	}

	@Override
	public int modify(ContactDTO contact) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int remove(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
