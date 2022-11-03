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

	<div>${board.boardNo}</div>
	<div>${board.writer}</div>
	<div>${board.title}</div>
	<div>${board.content}</div>

	<hr>
	
	<div>
		<input type="button" value="수정" id="btn_edit">
		<input type="button" value="목록" id="btn_list">
		<input type="button" value="삭제" id="btn_remove">
		<script>
		$('#btn_remove').click(function(event){
			if(confirm('게시글을 삭제할까요?')){
				location.href = '${contextPath}/remove.do?boardNo=${board.boardNo}';
			}
		</script>
	</div>

</body>
</html>