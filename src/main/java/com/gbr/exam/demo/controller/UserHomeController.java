package com.gbr.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserHomeController {
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
}
