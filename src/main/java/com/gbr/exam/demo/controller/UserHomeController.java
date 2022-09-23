package com.gbr.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserHomeController {
	
	private int count;
	
	public UserHomeController() {
		count = -1;
	}
	
	// 현재 카운트의 값 받아오기
	@RequestMapping("/user/home/getCount")
	@ResponseBody
	public int getCount() {
		return count;
	}	
	
	// count의 값을 매개변수(count) 이용하여 바꿔주기
	@RequestMapping("/user/home/doSetCount")
	@ResponseBody
	public String doSetCount(int count) {
		
		// count가 옵션이 됨. !!.. ㅠ .ㅠ.ㅠ.ㅠ.ㅠ...
		// http://localhost:8081/user/home/doSetCount?count=10
		
		this.count = count;
		return "count의 값이"+ this.count + "(으)로 초기화 됨 ! ";
		
		
		
	}
}
