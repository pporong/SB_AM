package com.gbr.exam.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Controller
public class UserHomeController {
	// string
	@RequestMapping("/user/home/getString")
	@ResponseBody
	public String getString() {
		return "hi";
	}
	// int
	@RequestMapping("/user/home/getInt")
	@ResponseBody
	public int getInt() {
		return 10;
	}	
	// float
	@RequestMapping("/user/home/getFloat")
	@ResponseBody
	public float getFloat() {
		return 10.5f;
	}
	// double
	@RequestMapping("/user/home/getDouble")
	@ResponseBody
	public double getDouble() {
		return 10.5;
	}
	// boolean
	@RequestMapping("/user/home/getBoolean")
	@ResponseBody
	public boolean getBoolean() {
		return true;
	}
	// char
	@RequestMapping("/user/home/getChar")
	@ResponseBody
	public char getChar() {
		return 'a';
	}
	// map
	@RequestMapping("/user/home/getMap")
	@ResponseBody
	public Map getMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("철수나이", 56);
		map.put("영희나이", 43);
		
		return map;
	}
	// list
	@RequestMapping("/user/home/getList")
	@ResponseBody
	public List<String> getList() {
		List<String> list = new ArrayList<>();
		list.add("배고프다");
		list.add("집에 가고싶다 히히");
		
		return list;
	}
	// article
	@RequestMapping("/user/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article(18,"배고픔ㅋ");
		Article article2 = new Article();
		
		return article;
	}
	// articles
	@RequestMapping("/user/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		Article article1 = new Article(18,"배고픔ㅋ");
		Article article2 = new Article(2, "제목2");
		
		List<Article> articles = new ArrayList<>();
		articles.add(article1);
		articles.add(article2);
		
		return articles;
	}
}

@Data
@NoArgsConstructor 
@AllArgsConstructor // 생성자 만들어라~
class Article {
	private int id;
	private String title;	
}
