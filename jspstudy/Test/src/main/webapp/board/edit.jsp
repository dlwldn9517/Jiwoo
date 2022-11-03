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
	
	<div>
		<form id="frm_edit" action="${contextPath}/modify.do" method="post">
			<table border="1">
				<tbody>
					<tr>
						<td>순번</td>
						<td>
							<input type="text" id="boardNo" name="boardNo" >	
						</td>
					</tr>
					<tr>
						<td><div>작성자 ${board.writer}</div></td>
					</tr>
					<tr>
						<td>제목</td>
						<td>
							<input type="text" id="title" name="title">	
						</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea id="content" name="content" row="30" cols="50"></textarea>	
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="submit" value="수정">
							<input type="button" value="목록" id="btn_list">
							<script>
								$('#btn_list').click(function(event) {
									location.href = '${contextPath}/list.do';
								});
							</script>
							<input type="button" value="삭제" id="btn_remove">
							<script>
								$('#btn_remove').click(function(event) {
									location.href = '${contextPath}/remove.do';
								});
							</script>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>