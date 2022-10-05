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
	public ResultData<Integer> writeArticle(int memberId, String title, String body) {
		articleRepository.writeArticle(memberId, title, body);	
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", "게시물이 생성되었습니다. :)", "id", id);
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
	public ResultData<Article> modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getArticle(id);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다. :)", id), "article", article);
	}

	public ResultData actorCanModify(int loginedMemberId, Article article) {	
		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-B", "!! 해당 게시물에 대한 수정 권한이 없습니다. !!");
		}
		return ResultData.from("S-1", " == 수정 가능 ==");
	}

}
