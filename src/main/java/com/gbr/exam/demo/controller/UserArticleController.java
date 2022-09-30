package com.gbr.exam.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.demo.service.UserArticleService;
import com.gbr.exam.demo.util.Ut;
import com.gbr.exam.demo.vo.Article;
import com.gbr.exam.demo.vo.ResultData;

@Controller
public class UserArticleController {

	// 인스턴스 변수
	@Autowired
	private UserArticleService userArticleService;

	/* == Action Method == */

	// Add = write
	@RequestMapping("/user/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(String title, String body) {
		if (Ut.empty(title)) {
			return ResultData.from("F-1", "!! 제목이 입력되지 않았습니다. 입력 해 주세요 !!");
		} if (Ut.empty(body)) {
			return ResultData.from("F-2", "!! 내용이 입력되지 않았습니다. 입력 해 주세요 !!");
		}
		
		ResultData<Integer> writeArticleRd = userArticleService.writeArticle(title, body);

		int id = (int) writeArticleRd.getData1();
		
		Article article = userArticleService.getArticle(id);
		
		return ResultData.from(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), article);
	}

	// list = articles
	@RequestMapping("/user/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {
		List<Article> articles = userArticleService.getArticles();
		
		return ResultData.from("S-1", "Article List" , articles);
	}

	// Delete
	@RequestMapping("/user/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return id + " 번 게시물은 존재하지 않습니다. :( ";
		}

		userArticleService.deleteArticle(id);
		return id + " 번 게시물이 삭제되었습니다. :)";
	}

	// Modify
	@RequestMapping("/user/article/doModify")
	@ResponseBody
	public Object doModify(int id, String title, String body) {

		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return id + " 번 게시물은 존재하지 않습니다. :(";
		}

		userArticleService.modifyArticle(id, title, body);

		return article;
	}

	// Detail
	@RequestMapping("/user/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물입니다. :)", id), article);
	}

}
