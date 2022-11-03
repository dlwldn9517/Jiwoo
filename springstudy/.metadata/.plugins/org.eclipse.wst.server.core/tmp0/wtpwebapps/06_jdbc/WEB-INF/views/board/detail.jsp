<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {
		
		var frm = $('#frm_btn');
		
		// 편집화면으로 이동
		$('#btn_edit').click(function() {
			frm.attr('action', '${contextPath}/brd/edit');	// 클릭하면 아래 있는 폼 속성에 action을 추가
			frm.submit();	// 서브밋 호출, action을 동작시키는 것은 submit
		});
		
		// 삭제
		$('#btn_remove').click(function() {
			if(confirm('삭제할까요?')) {
				frm.attr('action', '${contextPath}/brd/remove');  // 클릭하면 아래 있는 폼 속성에 action을 추가
				frm.submit();	// 서브밋 호출, action을 동작시키는 것은 submit
				return;
			}
		});
		
		// 목록
		$('#btn_list').click(function() {
			location.href = '${contextPath}/brd/list';
		});
		
	});

</script>
</head>
<body>

	<ul>
		<li>글번호 : ${board.board_no}</li>
		<li>제목 : ${board.title}</li>
		<li>작성자 : ${board.writer}</li>
		<li>작성일 : ${board.create_date}</li>
		<li>수정일 : ${board.modify_date}</li>
	</ul>
	<div>
		${board.content}
	</div>
	
	<hr>
	
	<div>
		<form id="frm_btn"  method="post">
			<!-- 삭제할 때 글번호가 있어야 삭제가 가능하다. -->
			<input type="hidden" name="board_no" value="${board.board_no}">	<!-- hidden을 사용할 때 필수 : name과 id -->
			<input type="button" value="편집" id="btn_edit">
			<input type="button" value="삭제" id="btn_remove">	
			<input type="button" value="목록" id="btn_list">
		</form>
	</div>

</body>
</html>