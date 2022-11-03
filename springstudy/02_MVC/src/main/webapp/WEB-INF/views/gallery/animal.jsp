<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>고양이 냥이</h1>
	<img src="${contextPath}/resources/images/animal1.jpg" width="200px">
	
	<h1>강아지</h1>
	<img src="${contextPath}/assets/images/animal2.jpg" width="200px">
	
</body>
</html>