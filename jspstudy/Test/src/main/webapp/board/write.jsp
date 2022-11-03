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
		<form id="frm_write" action="${contextPath}/add.do" method="post">
			<table border="1">
				<tbody>
					<tr>
						<td>작성자</td>
						<td>
							<input type="text" id="writer" name="writer">	
						</td>
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
							<textarea id="content" name="content" rows="10" cols="50"></textarea>	
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<input type="submit" value="등록" id="btn_add">
							<script>
								$('#btn_add').click(function(event) {
									location.href = '${contextPath}/add.do';
								});
							</script>
							<input type="button" value="목록" id="btn_list">
							<script>
								$('#btn_list').click(function(event) {
									location.href = '${contextPath}/list.do';
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