package org.zerock.sample;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Component
@Data
@RequiredArgsConstructor
public class Restaurant {

// 의존성 주입의 3가지 방식
// 1.setter 주입 	@Setter(onMethod_ = {@Autowired} )
// 2.생성자 주입 (생성자를 만들거나 lombok사용 ->@AllArgsConstructor) 
// 3.필드 주입 @Autowired
// 4.@RequiredArgsConstrcktor 를 public class Restaurant위에 생성
// 4번째 방법의 경우 5이상에서 사용된다 final로 선언된 객체 찾아 실행해준다
	
	// chef 객체를 Restaurant에 의존성 주입
	//@Setter(onMethod = @__({ @Autowired }))
	//@Setter(onMethod_ = {@Autowired} )
	private final Chef chef;
	//private Chef chef;

}
