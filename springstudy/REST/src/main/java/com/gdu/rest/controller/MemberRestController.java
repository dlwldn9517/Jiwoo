package com.gdu.rest.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gdu.rest.domain.MemberDTO;
import com.gdu.rest.service.MemberService;

/*
	REST : REpresentational State Transfer
	
	1. 자원을 정의하고 자원의 주소를 지정하는 방식에 대한 하나의 형식이다.
	2. REST 방식을 따르는 시스템을 "RESTful하다"라고 표현한다.
	3. 동작을 URL + Method 조합으로 결정한다.
	4. 파라미터가 URL에 경로처럼 포함된다. (?를 사용하지 않는다.)
	5. CRUD 처리 방식
				  URL			Method
		1) 삽입  /members   	POST
		2) 목록  /members   	GET
		3) 상세  /members/1   	GET
		4) 수정  /members   	PUT		- POST방식과 동일하게
		5) 삭제  /members/1   	DELETE  - 삭제 전용 메소드
*/

@RestController	 // 이 컨트롤러는 모든 메소드에 @ResponseBody 애너테이션을 추가한다.
public class MemberRestController {
	
	@Autowired	// @Autowired 되는 이유? 멤버서비스임플에 @서비스 애너테이션을 붙였기 때문
	private MemberService memberService;
	
	// 삽입
	@PostMapping(value="/members", produces="application/json")				
	public Map<String, Object> addMember(@RequestBody MemberDTO member, HttpServletResponse response) {	// 1. MemberDTO  2. Map으로 받아서 여러 필드를 넘겨줄 수 있다.
		// @RequestBody : 바디에 있는 요청 데이터를 본문에 member를 포함시켜서 전송한 걸 가져옴
		// HttpServletResponse response 에러를 받아서 보여주는 용도
		return memberService.register(member, response);
	}
	
	// 목록
	@GetMapping(value="/members/page/{page}", produces="application/json")
	public Map<String, Object> getMemberList(@PathVariable(value="page", required=false) Optional<String> opt) {	// @PathVariable : 경로에 포함된 변수
		int page = Integer.parseInt(opt.orElse("1"));	// page값이 false거나 null이라서 
		return memberService.getMemberList(page);
	}
	
	// 상세
	@GetMapping(value="/members/{memberNo}", produces="application/json")
	public Map<String, Object> getMember(@PathVariable(value="memberNo", required = false) Optional<String>opt){
		int memberNo = Integer.parseInt(opt.orElse("0"));
		return memberService.getMemberByNo(memberNo);
	}
	
	// 수정
	@PutMapping(value="/members", produces="application/json")
	public Map<String, Object> modifyMember(@RequestBody Map<String, Object> map, HttpServletResponse response) {
		return memberService.modifyMember(map, response);
	}
	
	// 삭제
	@DeleteMapping(value="/members/{memberNoList}", produces="application/json")
	public Map<String, Object> removeMemberList(@PathVariable String memberNoList) {
		return memberService.removeMemberList(memberNoList);
	}
	
}
