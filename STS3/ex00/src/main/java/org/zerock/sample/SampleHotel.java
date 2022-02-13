package org.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Component
@ToString
@RequiredArgsConstructor
public class SampleHotel {

	
	private final Chef chef;

	// 생성자 주입을 통한 의존성 주입

//	public SampleHotel(Chef chef) {
//		super();
//		this.chef = chef;
//	}

	
	
	
}
