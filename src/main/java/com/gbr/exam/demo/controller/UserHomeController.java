package com.gbr.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.demo.vo.Article;

@Controller
public class UserHomeController {
	
	@RequestMapping("/user/home/main")
	@ResponseBody
	public String main() {
		return "하잉";
	}	
}
