package com.gdu.app07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app07.repository.BoardDAO;
import com.gdu.app07.service.BoardService;
import com.gdu.app07.service.BoardServiceImpl;

@Configuration
public class BoardAppContext {

	// 06_Jdbc의 @Repository 대신 추가한 Bean
	@Bean	// 컨테이너에 BoardDAO를 등록시켜라 - 2곳 중 1곳에서만 설정하면 됨
	public BoardDAO dao() {
		return new BoardDAO();
	}
	
	// 06_Jdbc의 @Service 대신 추가한 Bean
	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
}
