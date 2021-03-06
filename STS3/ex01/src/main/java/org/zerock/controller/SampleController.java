package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;


@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	// 클래스를 파라미터로 받는 경우
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		
		// System.out.printf("여기는 ex01 : ", dto);
		log.info("" + dto);
		
		return "ex01";
	}
	
	
	// 파라미터의 이름을 다르게 하고 싶을 때
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		log.info("name : " + name);
		log.info("age: " + age);

		return "ex02";
	}
	
	
	// 동일한 이름의 파라미터가 여러개 전달되는 경우
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
		
		// sample/ex02List?ids=111&ids=222&ids=333 를 url에 입력
		// ids : [111, 222, 333]
		log.info("ids : " + ids);
		
		
		return "ex02List";
	}
	
	// 배열의 경우
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		
		log.info("array ids : "+ Arrays.toString(ids));
		//array ids : [111, 222, 333]
		
		return "ex02Array";
	}
	
	// dto를 다중 파라미터로 받아오는 경우
	@GetMapping("/ex02Bean")
	public String ex02Bean(@ModelAttribute("slist") SampleDTOList list) {
		
		//입력 : http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb
		log.info("DTOList : " + list);
		//출력 : DTOList : SampleDTOList(list=[SampleDTO(name=aaa, age=0), SampleDTO(name=null, age=0), SampleDTO(name=bbb, age=0)])
		
		return "sample/ex02Bean";
	}
	
	// Date 변환 방법1. @InitBinder 파라미터 변수 타입 변환기 
	// Date 변환 방법2. @DateTimeFormat -> TodoDTO 참조 
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		// 받아온 string 파라미터 형태
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
		
		// 변환되는 형태
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
		
	}
	*/
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		
		log.info("todo" + todo );
		//입력 : http://localhost:8080/sample/ex03?title=test&dueDate=2018-01-01
		//출력 : todoTodoDTO(title=test, dueDate=Mon Jan 01 00:00:00 KST 2018)
		
		return "ex03";
	}
	
	//model사용법
	public String home(Model model) {
		
		model.addAttribute("serverTime", new java.util.Date());
		
		return "home";
	}
	
	// @ModelAttribute 어노테이션 
	@GetMapping({"/ex04", "/ex044"})
	public void ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		// http://localhost:8080/sample/ex04?name=aaa&age=11&page=9 
		// dto만 표출되고 int형은 전달되지 나오지 않음 
		// 그래서 @ModelAttribute를 사용해 model에 담아 화면으로 전달해준다. 
		// http://localhost:8080/sample/ex044?name=aaa&age=11&page=9
		// return "/sample/ex04";
		
	}
	
	// Redirect : 1회성 전달 주로 화면 이동에 ㅏㅅ용됨
	@GetMapping("/re1")
	public String re1() {
		log.info("re1.............");
	
		//response.sendRedirect("...")로 써왔던 것을 스프링에서는 아래와 같이 쓸 수 있다.
		return "redirect:/sample/re2";
	}
	
	@GetMapping("/re2")
	public void re2() {
		log.info("re2.............");
	}
	
	// json 객체 데이터
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06...........");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		// {"name":"홍길동","age":10}
		
		return dto;
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info(file.getOriginalFilename());
			log.info(file.getSize());
			log.info(file.getContentType());
			
		});
		
		log.info("exUploadPost");
	}
	
	
	
	
	
	
	
	
}
