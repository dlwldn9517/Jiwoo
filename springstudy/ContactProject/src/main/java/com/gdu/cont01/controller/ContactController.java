package com.gdu.cont01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.cont01.domain.ContactDTO;
import com.gdu.cont01.service.ContactService;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("c/list")
	public String list(Model model) {
		model.addAttribute("cards", contactService.findAllContacts());
		return "card/list";
	}
	
	@GetMapping("c/write")
	public String write() {
		return "card/write";
	}
	
	@PostMapping("c/add")
	public String add(ContactDTO contact) {
		contactService.register(contact);
		return "redirect:/c/list";
	}
	
	
}
