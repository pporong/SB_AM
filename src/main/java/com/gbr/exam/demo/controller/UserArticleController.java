package com.gbr.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.demo.service.UserArticleService;
import com.gbr.exam.demo.vo.Article;

@Controller
public class UserArticleController {

	// 인스턴스 변수
	@Autowired
	private UserArticleService userArticleService;
	
	/* ========================== Action Method == */
	
	// Add
	@RequestMapping("/user/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = UserArticleService.writeArticle(title, body);

		return article;
	}

	// list = articles
	@RequestMapping("/user/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		return articles;
	}

	// Delete
	@RequestMapping("/user/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = getArticle(id);

		if (article == null) {
			return id + " 번 게시물은 존재하지 않습니다. :( ";
		}

		UserArticleService.deleteArticle(id);

		return id + " 번 게시물이 삭제되었습니다. :)";
	}
	
	// Modify
	@RequestMapping("/user/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {

		Article article = getArticle(id);

		if (article == null) {
			return id + " 번 게시물은 존재하지 않습니다. :(";
		}

		UserArticleService.modifyArticle(id, title, body);

		return article;
	}
	
	// Detail
	@RequestMapping("/user/article/getArticle")
	@ResponseBody
	public Object getArticleAction(int id) {

		Article article = getArticle(id);

		if (article == null) {
			return id + " 번 게시물은 존재하지 않습니다. :( ";
		}

		return article;
	}






}
