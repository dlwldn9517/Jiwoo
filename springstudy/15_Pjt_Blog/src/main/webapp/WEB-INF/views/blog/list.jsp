<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />	<!-- scope="page" 생략가능 -->
<!-- 
   ajax로 page 목록을 만든다는 뜻 = javascript로 전부 다 만든다는 뜻. 날짜/시간 형태로 바꿔줄 수 있는 코드가 어려워서
   js에 moment-with-locales.js 라는 라이브러리 넣어놓았음
 -->

<%-- 헤더.jsp에서 request.getParameter("title")랑 연결 --%>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그목록" name="title"/>
</jsp:include>

<div>
	
	<%--
	<c:if test="${loginUser != null}">	// 관리자만 작성했으면 좋겠어 -> test="${loginUser != admin}"
		<input type="button" value="블로그 작성하기" onclick="${contextPath}/blog/write">	로그인이랑 작성하기를 접목 시킨 것
	</c:if>
	--%>
	
	<h1>블로그 목록(전체 ${totalRecord}개)</h1>
	
	<div>
		<input type="button" value="블로그 작성하기" onclick="location.href='${contextPath}/blog/write'"> <!-- 로그인 한 사람만 작성 버튼이 보인다. 로그인 안하면 작성 못해 -->
	</div>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>				
					<td>제목</td>				
					<td>조회수</td>				
					<td>작성일</td>				
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${blogList}" var="blog" varStatus="vs">
					<tr>
						<td>${beginNo - vs.index}</td>	<!-- 인덱스값 사용해서 순번 만들어줌 -->
						<td><a href="${contextPath}/blog/increse/hit?blogNo=${blog.blogNo}">${blog.title}</a></td>
						<td>${blog.hit}</td>
						<td>${blog.createDate}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4">
						${paging}	<!-- 서비스임플에서 'paging'란 이름에 싣었다 -->
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
	
</div>

<!-- footer jsp 따로 할거면 닫아주는 바디와 html은 해당 페이지에서 닫아야 함 -->
</body>
</html>