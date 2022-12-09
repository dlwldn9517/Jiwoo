package com.gdu.app02.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app02.domain.CommentDTO;

public interface CommentService {

	public Map<String, Object> getCommentCount(int blogNo);
	public Map<String, Object> addComment(CommentDTO comment);
	public Map<String, Object> getCommentList(HttpServletRequest request);	// blogNo, begin, end 모두 필요해서 request로 가져오고 싶은거 가져와라
	public Map<String, Object> removeComment(int commentNo);
	public Map<String, Object> addReply(CommentDTO reply);
	
}
