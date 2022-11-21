package com.gdu.app11.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmpService {
								// Controller에서만 Request, Response, Session, Model 선언할 수 있다.
								// Controller에 넘기지 않고, 내가 응답하겠다. (= Model)
	public void findAllEmployees(HttpServletRequest request, Model model); 	// 선언 아니고, Controller에서 받아오는거다.
	public void findEmployees(HttpServletRequest request, Model model);
	public Map<String, Object> selectAutoCompleteList(HttpServletRequest request);
}
