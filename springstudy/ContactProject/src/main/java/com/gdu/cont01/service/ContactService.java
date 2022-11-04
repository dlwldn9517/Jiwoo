package com.gdu.cont01.service;

import java.util.List;

import com.gdu.cont01.domain.ContactDTO;

public interface ContactService {
	
	public List<ContactDTO> findAllContacts();
	public ContactDTO findContactByNo(int no);
	public int register(ContactDTO contact);
	public int modify(ContactDTO contact);
	public int remove(int no);
	
}
