package com.gdu.app02.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app02.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/blog/list")
	public String list(HttpServletRequest request, Model model)	{
		model.addAttribute("request", request);	// model에 request를 저장하기
		blogService.getBlogList(model);			// model만 넘기지만 이미 model에 request가 들어 있어서 서비스단에서 model로부터 request를 꺼낸다.
		return "blog/list";						// 이렇게 하면 모든 서비스의 매개변수를 모델로 통일 가능. 
												// 서비스 하나당 메소드 하나 있을 때 매개변수의 통일이 필요한데 모델 하나로만 쓴다. (request response 각각 필요한거 담아서 사용 가능)
	}
	
	@GetMapping("/blog/write")
	public String write() {
		return "blog/write";
	}
	
	@ResponseBody
	@PostMapping(value="/blog/uploadImage", produces="application/json") // 이미지 받아올 수 있는 request ==> multipartRequest
	public Map<String, Object> uploadImage(MultipartHttpServletRequest multipartRequest) {	// json 반환 - MAP (잭슨 있으니까)
		return blogService.saveSummernoteImage(multipartRequest);
	}
	
	@PostMapping("/blog/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		blogService.saveBlog(request, response);
	}
	
	@GetMapping("/blog/increse/hit")
	public String increseHit(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo) {
		int result = blogService.increseBlogHit(blogNo);
		if(result > 0) {	// 조회수 증가 성공하면 상세보기로 이동
			return "redirect:/blog/detail?blogNo=" + blogNo;
		} else {			// 조회수 증가 실패하면 목록보기로 이동
			return "redirect:/blog/list";	
		}
	}
	
	@GetMapping("/blog/detail")	// 상세보기
	public String detail(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo, Model model) {
		model.addAttribute("blog", blogService.getBlogByNo(blogNo));
		return "blog/detail";
	}
	
	@PostMapping("/blog/edit")
	public String edit(int blogNo, Model model) {	// blogNo가 안올수가 없다 <input> name에 blog.blogNo로 입력해서 파라미터로 넘어옴
		model.addAttribute("blog", blogService.getBlogByNo(blogNo));	// 수정하러 갈 때 조회수 증가하는 것을 막음
		return "blog/edit";
	}
	
	@PostMapping("/blog/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		blogService.modifyBlog(request, response);	// 수정 후 상세보기로
	}
	
	@PostMapping("/blog/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		blogService.removeBlog(request, response);	// 삭제 후 목록보기로
	}
	
}
