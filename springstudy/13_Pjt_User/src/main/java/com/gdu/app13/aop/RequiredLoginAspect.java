package com.gdu.app13.aop;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component	// aop가 동작하기 위해서는 만들고 @Component 등록하고 해당 컴포넌트 동작시키는 @EnableAspcetJAutoProxy가 필요하다
@EnableAspectJAutoProxy
@Aspect	

/* 
@EnableAspectJAutoProxy
안녕, 난 aspect를 자동으로 동작시키는 애너테이션이야.
*/

public class RequiredLoginAspect {

	// @Pointcut : 특정 조건에 의해 필터링된 조인포인트, 수많은 조인포인트 중에 특정 메소드에서만 횡단 공통기능을 수행시키기 위해서 사용한다.
	@Pointcut("execution(* com.gdu.app13.controller.*Controller.requiredLogin_*(..))")	// .. : 매개변수 어떤것이든 상관없다.
	public void requiredLogin() { }
	// 
	
	@Before("requiredLogin()")	// 포인트컷 실행 전에 requiredLogin() 메소드 수행
	public void requiredLoginHandler(JoinPoint joinPoint) throws Throwable {
		
		// 로그인이 되어 있는지 확인하기 위해서 session이 필요하므로,
		// request가 필요하다.
		// 응답을 만들기 위해서 response도 필요하다.
		ServletRequestAttributes servletWebRequest = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request= servletWebRequest.getRequest();
		HttpServletResponse response = servletWebRequest.getResponse();
		
		// 세션
		HttpSession session = request.getSession();
		
		// 로그인 여부 확인
		if( session.getAttribute("loginUser") == null ) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("if(confirm('로그인이 필요한 기능입니다. 로그인 하시겠습니까?')) {");
			out.println("location.href='"+ request.getContextPath() +"/user/login/form';");	// 로그인페이지로 이동시켜주는 요청
			out.println("} else {");
            out.println("history.back();");	// 로그인창으로 이동
            out.println("}");
            out.println("</script>");
			out.close();
		}
	}
	
	
}
