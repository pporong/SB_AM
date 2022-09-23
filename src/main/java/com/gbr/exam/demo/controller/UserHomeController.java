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
}
