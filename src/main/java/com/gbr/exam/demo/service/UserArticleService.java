package com.gbr.exam.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbr.exam.demo.repository.ArticleRepository;
import com.gbr.exam.demo.util.Ut;
import com.gbr.exam.demo.vo.Article;
import com.gbr.exam.demo.vo.ResultData;

@Service
public class UserArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	public UserArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	/* == Service Method == */
	// list
	public List<Article> getArticles() {
		return articleRepository.getArticles();
	}
	
	// write
	public ResultData writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);	
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 생성되었습니다. :)", id), id);
	}
	
	// detail
	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}
	
	// delete
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	// modify
	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

}
