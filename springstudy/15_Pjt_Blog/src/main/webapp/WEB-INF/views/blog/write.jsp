<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그작성" name="title"/>
</jsp:include>

<script>
	
	// 4,5행에 있는거 없을 때 대신 사용할 수 있게
	// contextPath를 반환하는 자바스크립트 함수
	function getContextPath() {
		var begin = location.href.indexOf(location.origin) + location.origin.length;	// location.href에서 인덱스를 찾아라
		var end = location.href.indexOf("/", begin + 1);	// 어디서부터 찾아야 하는지 지정
		return location.href.substring(begin, end);
	}
	
	$(document).ready(function(){
		
		// summernote
		$('#content').summernote({	// id="content"가 summernote로 바뀐다.
			width: 800,
			height: 400,
			lang: 'ko-KR',
			toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['link', 'picture', 'video']]
			],
			callbacks: {
				// summernote 편집기에 이미지를 로드할 때 이미지는 function의 매개변수 files로 전달됨
				onImageUpload: function(files) {	// files HDD로 보내고 싶으면 이미지를 ajax 처리하고 그 경로를 받아오라
					
					// 동시에 여러 이미지를 올릴 수 있음
					for(let i = 0; i < files.length; i++) {
						
						// 이미지를 ajax를 이용해서 서버로 보낼 때 가상 form 데이터 사용
						// ajax를 사용하는 이유? 이미지를 DB에 저장하면 용량이 너무 커서
						// 이미지를 하드에 저장하고, DB에는 하드에 저장한 경로를 받아온다.
						var formData = new FormData();
						formData.append('file', files[i]);  // 파라미터 file, summernote 편집기에 추가된 이미지가 files[i]임
						
						// 이미지를 HDD에 저장하고 경로를 받아오는 ajax
						$.ajax({
							type: 'post',
							url: getContextPath() + '/blog/uploadImage',
							data:formData,
							contentType: false,	// ajax 이미지 첨부용
							processData: false,	// ajax 이미지 첨부용
							dataType: 'json',	// HDD에 저장된 이미지의 경로를 json으로 받아옴
							success: function(resData){
								
								/*
									resData의 src 속성값이 ${contextPath}/load/image/aaa.jpg인 경우
									<img src="${contextPath}/load/image/aaa.jpg"> 태그가 만들어진다.
									
									mapping=${contextPath}/load/image/aaa.jpg인 이미지의 실제 위치는
									location=C:\\upload\\aaa.jpg이므로 이 내용을
									servlet-context.xml에서 resource의 mapping값과 location값으로 등록해 준다.
									(스프링에서 정적 자원 표시하는 방법은 servlet-context.xml에 있다.)
								*/
								$('#content').summernote('insertImage', resData.src);	// map에 담은 src
								
								/*
									어떤 파일이 HDD에 저장되어 있는지 목록을 저장해 둔다.
									블로그를 등록할 때 써머노트에서 사용한 파일명도 함께 등록한다.
								*/
								$('#summernote_image_list').append($('<input type="hidden" name="summernoteImageNames" value="' + resData.filesystem + '">'))
								
							}
						});	// ajax
					}	// for
				}	// onImageUpload
			}	// callbacks
		});
		
		// 목록
		$('#btn_list').click(function(){
			location.href = getContextPath() + '/blog/list';
		});
		
		// 서브밋
		$('#frm_write').submit(function(event){
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				event.preventDefault();  // 서브밋 취소
				return;  // 더 이상 코드 실행할 필요 없음
			}
		});
		
	});
	
</script>

	<div>
	
		<h1>작성 화면</h1>
	
		<form id="frm_write" action="${contextPath}/blog/add" method="post">
	
			<div>
				<label for="title">제목</label>
				<input type="text" name="title" id="title">
			</div>
			
			<div>
				<label for="content">내용</label>
				<textarea name="content" id="content"></textarea>				
			</div>
			
			<!-- 써머노트에서 사용한 이미지 목록(등록 후 삭제한 이미지도 우선은 모두 올라감: 서비스단에서 지움) -->
			<div id="summernote_image_list"></div>
			
			<div>
				<button>작성완료</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" id="btn_list">
			</div>
		
		</form>
		
	</div>

</body> <!-- footer가 없으니까 살려야 함 -->
</html>