<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
   String title = opt.orElse("Welcome");
   pageContext.setAttribute("title", title);   // EL사용을 위함(${title})  이게 없으면 <%=title 퍼센트> 로 쓰면 된다. 자바변수 걍 가져다 쓰기
   pageContext.setAttribute("contextPath", request.getContextPath());   // == <taglib prefix="c">, <c:set>
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%-- list.jsp에 있는 <jsp:param value="블로그목록" name="title"/>에서 '블로그목록'을 가지고 와서 뜬다. --%>
<title>${title}</title>
<%-- 
	* ${contextPath}로 시작하는 값은 모두 매핑
	
	<resources mapping="/resources/**" location="/resources/" />
		script src="${contextPath}/resources => mapping 값(경로 X)
	
	location="/resources/"
	===> /resources/로 시작하는 모든 매핑은 resources 폴더 안에서 찾아라
		
--%>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="${contextPath}/resources/js/moment-with-locales.js"></script>	
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.css">
</head>
<body>

	<div>
		<h1>Welcome To My Blog</h1>
	</div>
	
</body>
</html>