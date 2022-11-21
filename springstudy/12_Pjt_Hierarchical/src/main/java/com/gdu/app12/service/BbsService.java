package com.gdu.app12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BbsService {

	public void findAllBbsList(HttpServletRequest request, Model model);
	public int addBbs(HttpServletRequest request);		// 왜 request로 했는가? ip를 알려면 request를 보내야 한다.
	public int addReply(HttpServletRequest request);
	public int removeBbs(int bbsNo);
}
