package com.gdu.staff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller
public class StaffController {

	@Autowired
	private StaffService staffService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@ResponseBody
	@GetMapping(value="/list.json", produces="application/json; charset=UTF-8")
	public List<StaffDTO> stfList() {
		return staffService.getstaffList();
	}
	
	// 방법 1
	@ResponseBody
	@PostMapping(value="/add", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> add(HttpServletRequest request) {
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		StaffDTO staff = new StaffDTO(sno, name, dept, 0);
		return staffService.addStaff(staff);
	}
	
	@ResponseBody
	@GetMapping(value="/query.json", produces="application/json; charset=UTF-8")
	public StaffDTO find(HttpServletRequest request) {
		String sno = request.getParameter("sno");
		return staffService.getfind(sno);
	}
	
	
	/*
	// 방법 2
	@ResponseBody
	@PostMapping(value="/add", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> add(@RequestParam(value="sno") String sno
									, @RequestParam(value="name" , required=false) String name
									, @RequestParam(value="dept") String dept) {
		StaffDTO staff = new StaffDTO(sno, name, dept, 0);
		return staffService.addStaff(staff);
	}
	
	// 방법 3
	@ResponseBody
	@PostMapping(value="/add", produces="text/plain; charset=UTF-8")
	public ResponseEntity<String> add(StaffDTO staff) {
		return staffService.addStaff(staff);
	}
	*/
	
	
}
