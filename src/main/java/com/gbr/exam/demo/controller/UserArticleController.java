package com.gbr.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.demo.vo.Article;

@Controller
public class UserArticleController {

	// 인스턴스 변수
	private int lastArticleId;
	private List<Article> articles;

	// 생성자
	public UserArticleController() {
		lastArticleId = 0;
		articles = new ArrayList<>();

		makeTestData();
	}

	/* ========================== Service Method == */
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			String title = "제목 " + i;
			String body = "내용 " + i;

			writeArticle(title, body);
		}
	}

	private Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}

		return null;
	}
	
	// 삭제
	private void deleteArticle(int id) {

		Article article = getArticle(id);

		articles.remove(article);
	}
	
	// 수정
	private void modifyArticle(int id, String title, String body) {
		Article article = getArticle(id);
		
		article.setTitle(title);
		article.setBody(body);	
	}
	
	// write
	private Article writeArticle(String title, String body) {

		int id = lastArticleId + 1;

		Article article = new Article(id, title, body);
		articles.add(article);
		lastArticleId = id;

		return article;
	}

	/* ========================== Action Method == */
	
	// Add
	@RequestMapping("/user/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		Article article = writeArticle(title, body);

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

		deleteArticle(id);

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

		modifyArticle(id, title, body);

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
