package com.gbr.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbr.exam.demo.repository.ArticleRepository;
import com.gbr.exam.demo.vo.Article;

@Service
public class UserArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public UserArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
		articleRepository.makeTestData();
	}
	
	/* == Service Method == */
	// list
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
	
	// write
	public Article writeArticle(String title, String body) {
		return articleRepository.writeArticle(title, body);
	}
	
	// delete
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	// modify
	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}
	
	// detail
	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}


}
