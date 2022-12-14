<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<style>
	/* 페이징 */
	#paging  {
		font-size: 12px;
		color: gray;
	}
	#paging span, #paging strong {
		margin: 0 3px;
	}
	.lnk_enable {
		cursor: pointer;
	}
	.lnk_enable:hover {
		color: limegreen;
	}
</style>
<script>
	
	$(function(){
		fn_checkAll();
		fn_checkOne();
		fn_add();
		fn_init();
		fn_list();
		fn_changePage();
		fn_detail();
		fn_modify();
		fn_remove();
	});
	
	function fn_checkAll(){
		$('#check_all').click(function(){
			$('.check_one').prop('checked', $('#check_all').prop('checked'));
		});
	}
	
	function fn_checkOne(){
		$(document).on('click', '.check_one', function(){
			let checkCount = 0;
			for(let i = 0; i < $('.check_one').length; i++){
				checkCount += $($('.check_one')[i]).prop('checked');				
			}
			$('#check_all').prop('checked', checkCount == $('.check_one').length);
		});
	}
	
	function fn_add(){
		$('#btn_add').click(function(){
			// 추가할 회원 정보를 JSON으로 만든다.
			let member = JSON.stringify({
				'id': $('#id').val(),
				'name': $('#name').val(),
				'gender': $(':radio[name=gender]:checked').val(),
				'address': $('#address').val()
			});
			// 추가할 회원 정보를 DB로 보낸다.
			$.ajax({
				/* 요청 */
				type: 'post',
				url: '${contextPath}/members',
				data: member,  // 파라미터 이름 없음(본문에 member를 포함시켜서 전송)
				contentType: 'application/json',  // 요청 데이터의 MIME-TYPE
				/* 응답 */
				dataType: 'json',
				success: function(resData){
					if(resData.insertResult > 0) {
						alert('회원이 등록되었습니다.');
						fn_list();
						fn_init();
					} else {
						alert('회원이 등록되지 않았습니다.');
					}
				},
				error: function(jqXHR){
					alert('에러코드(' + jqXHR.status + ') ' + jqXHR.responseText);
				}
			});
		});
	}
	
	function fn_init(){
		$('#id').val('').prop('readonly', false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#address').val('');
	}
	
	// 전역변수
	var page = 1;
	
	function fn_list(){
		$.ajax({
			type: 'get',
			url: '${contextPath}/members/page/' + page,
			dataType: 'json',
			success: function(resData){
				// 회원목록
				$('#member_list').empty();
				$.each(resData.memberList, function(i, member){
					var tr = '<tr>';
					tr += '<td><input type="checkbox" class="check_one" value="'+ member.memberNo +'"></td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M' ? '남자' : '여자') + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '<td><input type="button" value="조회" class="btn_detail" data-member_no="'+ member.memberNo +'"></td>';
					tr += '</tr>';
					$('#member_list').append(tr);
				});
				// 페이징
				$('#paging').empty();
				var naverPageUtil = resData.naverPageUtil;
				var paging = '<div>';
				// 이전 페이지
				if(page != 1) {
					paging += '<span class="lnk_enable" data-page="' + (page - 1) + '">&lt;이전</span>';
				}
				// 페이지번호
				for(let p = naverPageUtil.beginPage; p <= naverPageUtil.endPage; p++) {
					if(p == page){
						paging += '<strong>' + p + '</strong>';
					} else {
						paging += '<span class="lnk_enable" data-page="'+ p +'">' + p + '</span>';
					}
				}
				// 다음 페이지
				if(page != naverPageUtil.totalPage){
					paging += '<span class="lnk_enable" data-page="'+ (page + 1) +'">다음&gt;</span>';
				}
				paging += '</div>';
				// 페이징 표시
				$('#paging').append(paging);
			}
		});
	}
	
	function fn_changePage(){
		$(document).on('click', '.lnk_enable', function(){
			page = $(this).data('page');
			fn_list();
		});
	}
	
	function fn_detail(){
		$(document).on('click', '.btn_detail', function(){
			$.ajax({
				type: 'get',
				url: '${contextPath}/members/' + $(this).data('member_no'),
				dataType: 'json',
				success: function(resData){
					let member = resData.member;
					if(member == null){
						alert('해당 회원을 찾을 수 없습니다.');
					} else {
						$('#memberNo').val(member.memberNo);
						$('#id').val(member.id).prop('readonly', true);
						$('#name').val(member.name);
						$(':radio[name=gender][value='+ member.gender +']').prop('checked', true);
						$('#address').val(member.address);
					}
				}
			});
		});
	}
	
	function fn_modify(){
		$('#btn_modify').click(function(){
			// 수정할 회원정보를 JSON으로 만들기
			let member = JSON.stringify({
				memberNo: $('#memberNo').val(),
				name: $('#name').val(),
				gender: $(':radio[name=gender]:checked').val(),
				address: $('#address').val()
			});
			// 수정
			$.ajax({
				type: 'put',
				url: '${contextPath}/members',
				data: member,
				contentType: 'application/json',
				dataType: 'json',
				success: function(resData){
					if(resData.updateResult > 0){
						alert('회원 정보가 수정되었습니다.');
						fn_list();
					} else {
						alert('회원 정보가 수정되지 않았습니다.');
					}
				},
				error: function(jqXHR){
					alert('에러코드(' + jqXHR.status + ') ' + jqXHR.responseText);
				}
			});
		});
	}
	
	function fn_remove(){
		$('#btn_remove').click(function(){
			if(confirm('선택한 회원을 모두 삭제할까요?')){
				// 삭제할 회원번호
				let memberNoList = '';
				for(let i = 0; i < $('.check_one').length; i++){
					if( $($('.check_one')[i]).is(':checked') ) {
						memberNoList += $($('.check_one')[i]).val() + ',';  // 3,1,  (마지막 콤마 있음을 주의)
					}
				}
				memberNoList = memberNoList.substr(0, memberNoList.length - 1);  // 3,1  (마지막 콤마 자르기)
				$.ajax({
					type: 'delete',
					url: '${contextPath}/members/' + memberNoList,
					dataType: 'json',
					success: function(resData){
						if(resData.deleteResult > 0){
							alert('선택된 회원 정보가 삭제되었습니다.');
							fn_list();
						} else {
							alert('선택된 회원 정보가 삭제되지 않았습니다.');
						}
					}
				});
			}
		});
	}
	
</script>
</head>
<body>

	<h1>회원 관리</h1>
	<div>
		<input type="hidden" id="memberNo">
		<div>
			<label for="id">
				아이디 <input type="text" id="id">
			</label>
		</div>
		<div>
			<label for="name">
				이름 <input type="text" id="name">
			</label>
		</div>
		<div>
			<span>성별</span>
			<label for="male">
				<input type="radio" name="gender" id="male" value="M">남자
			</label>
			<label for="female">
				<input type="radio" name="gender" id="female" value="F">여자
			</label>
		</div>
		<div>
			<label for="address">
				주소
				<select id="address">
					<option value="">선택</option>
					<option value="서울">서울</option>
					<option value="경기">경기</option>
					<option value="인천">인천</option>
				</select>
			</label>
		</div>
		<div>
			<input type="button" value="초기화" onclick="fn_init()">
			<input type="button" value="등록하기" id="btn_add">
			<input type="button" value="수정하기" id="btn_modify">
		</div>
	</div>
	<hr>
	<div>
		<input type="button" value="선택삭제" id="btn_remove">
		<table border="1">
			<thead>
				<tr>
					<td><input type="checkbox" id="check_all"></td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td></td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<div id="paging"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>