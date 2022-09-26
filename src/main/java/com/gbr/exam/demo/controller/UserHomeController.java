package com.gbr.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.demo.vo.Article;

@Controller
public class UserHomeController {

	// article
	@RequestMapping("/user/home/getArticle")
	@ResponseBody
	public Article getArticle() {
		Article article = new Article(1, "반가룽", "1입니당");
		Article article2 = new Article(2, "제목2~~지롱", "내용2 라니까욤");
		
		return article;
	}
	
	// article's'
	@RequestMapping("/user/home/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		Article article1 = new Article(1, "반가룽", "1입니당");
		Article article2 = new Article(2, "제목2~~지롱", "내용2 라니까욤");
		
		List<Article> articles = new ArrayList<>();
		articles.add(article1);
		articles.add(article2);
		
		return articles;
	}
}
