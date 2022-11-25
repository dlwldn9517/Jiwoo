package com.gdu.app14.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.service.UploadService;

@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/upload/list")
	public String list(Model model) {
		model.addAttribute("uploadList", uploadService.getUploadList());	// list.jsp에서 (items="${uploadList}) == uploadList 이름으로 모델에 저장
		return "upload/list";
	}
	
	@GetMapping("/upload/write")
	public String write() {
		return "upload/write";
	}
	
	@PostMapping("/upload/add")
	public void add(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {	// 첨부가 있을 때는 MultipartHttpServletRequest 사용, 첨부할 때 일반 request는 사용 불가
		uploadService.save(multipartRequest, response);
	}
	
	@GetMapping("/upload/detail")
	// 매개변수에 서비스한테 넘겨줄 것이 필요해서 @RequestParam에 uploadNo를 담는다.
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";
	}
	
	@ResponseBody
	@GetMapping("/upload/download")
	public ResponseEntity<Resource> download(@RequestHeader("User-Agent") String userAgent, int attachNo) {	// int attachNo은 @RequestParam을 생략 가능하다.
		// 파라미터로 attachNo는 넘겨줘야 한다.
		return uploadService.download(userAgent, attachNo);
	}
	
	@ResponseBody
	@GetMapping("/upload/downloadAll")
	public ResponseEntity<Resource> downloadAll(@RequestHeader("User-Agent") String userAgent, @RequestParam("uploadNo") int uploadNo) {
		return uploadService.downloadAll(userAgent, uploadNo);
	}
	
	@PostMapping("/upload/edit")
	public String edit(@RequestParam("uploadNo") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/edit";
	}
	
	@PostMapping("/upload/modify")
	public void modify(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		uploadService.modifyUpload(multipartRequest, response);
	}
	
	@GetMapping("/upload/attach/remove")
	public String attachRemove(@RequestParam("uploadNo") int uploadNo, @RequestParam("attachNo") int attachNo) {
		uploadService.removeAttachByAttachNo(attachNo);
		return "redirect:/upload/detail?uploadNo=" + uploadNo;	// detail.jsp로 갈때 uploadNo가 필요하다. detail.jsp에서 파라미터로 uploadNo를 받아갈 수 있게 해줘야함
	}
	
	@PostMapping("/upload/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		uploadService.removeUpload(request, response);
	}
	
}
