package com.gbr.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserHomeController {
	
	private int count;
	
	public UserHomeController() {
		count = 0;
	}
	
	@RequestMapping("/user/home/main")
	@ResponseBody
	public String showMain() {
		return "하ㅓ하하하하하잉 푸헹헹";
	}
	
	@RequestMapping("/user/home/main2")
	@ResponseBody
	public String showMain2() {
		return "반갑소";
	}
	
	@RequestMapping("/user/home/main3")
	@ResponseBody
	public String showMain3() {
		return "조심해서 가시오";
	}	
	
	// 새로고침 할 때마다 숫자 증가
	@RequestMapping("/user/home/main4")
	@ResponseBody
	public int showMain4() {
		return count++;
	}	
	
	// count 값 초기화
	@RequestMapping("/user/home/main5")
	@ResponseBody
	public String showMain5() {
		count = 0;
		return "count의 값이 0으로 초기화 됨 ! ";
	}
}
