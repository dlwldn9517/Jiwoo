package com.gdu.app07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.app07.domain.ContactDTO;
import com.gdu.app07.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("b/list")
	public String list(Model model) {
		model.addAttribute("boards", contactService.listAllBoards());
		return "board/list";
	}
	
	@GetMapping("b/write")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("b/add")
	public String add(ContactDTO contact) {
		contactService.saveBoard(contact);
		return "redirect:/b/list";
	}
	
	
}
