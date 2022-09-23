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

	// article
	@RequestMapping("/user/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article(1, "제목1", "내용1");
		Article article2 = new Article(2, "제목2", "내용2");
		
		return article;
	}
	// articles
	@RequestMapping("/user/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		Article article1 = new Article();
		Article article2 = new Article();
		
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
	private String body;
}
