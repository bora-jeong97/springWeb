package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {

	
	private String title;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date dueDate;
	// 입력 : http://localhost:8080/sample/ex03?title=test&dueDate=2018/01/02
	// 출력 : todoTodoDTO(title=test, dueDate=Tue Jan 02 00:00:00 KST 2018)
	
}
