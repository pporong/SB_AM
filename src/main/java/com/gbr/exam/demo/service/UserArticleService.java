package com.gbr.exam.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public List<Article> getForPrintArticles(int actorId) {
		List<Article>  articles = articleRepository.getArticles();
		
		for (Article article : articles) {
			updateForPrintData(actorId, article);
		}	
		return articles;
	}
	
	// write
	public ResultData<Integer> writeArticle(int memberId, String title, String body) {
		articleRepository.writeArticle(memberId, title, body);	
		int id = articleRepository.getLastInsertId();
		
		return ResultData.from("S-1", "게시물이 생성되었습니다. :)", "id", id);
	}
	
	// detail
	public Article getForPrintArticle(int actorId, int id) {	
		Article article =  articleRepository.getForPrintArticle(id);
		
		updateForPrintData(actorId, article);
		
		return article;
	}
	
	//
	private void updateForPrintData(int actorId, Article article) {
		if (article == null) {
			return;
		}
		ResultData actorCanDeleteRd = actorCanDelete(actorId, article);
		article.setExtra__actorCanDelete(actorCanDeleteRd.isSuccess());
	}

	// delete
	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	// modify
	public ResultData<Article> modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getForPrintArticle(0, id);
		
		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다. :)", id), "article", article);
	}
	

	
	// 수정 권한
	public ResultData actorCanModify(int loginedMemberId, Article article) {	
		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-B", "!! 해당 게시물에 대한 수정 권한이 없습니다. !!");
		}
		return ResultData.from("S-1", " == 수정 가능 ==");
	}
	
	// 삭제 권한
	public ResultData actorCanDelete(int actorId, Article article) {	
		if (article == null) {
			return ResultData.from("F-1", "해당 게시물이 존재하지 않습니다.");
		}
		if(article.getMemberId() != actorId) {
			return ResultData.from("F-2", "!! 해당 게시물에 대한 삭제 권한이 없습니다. !!");
		}
		
		return ResultData.from("S-1", " == 삭제 가능 ==");
	}
	


}
