package com.gbr.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

	// Add = write
	@RequestMapping("/user/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpSession httpsession, String title, String body) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		// 로그인 여부
		if (httpsession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");
		} // 이미 로그아웃 상태
		if (isLogined == false) {
			return ResultData.from("F-A", "!! 로그인 후 이용 할 수 있습니다. !!");
		}
		
		if (Ut.empty(title)) {
			return ResultData.from("F-1", "!! 제목이 입력되지 않았습니다. 입력 해 주세요 !!");
		} if (Ut.empty(body)) {
			return ResultData.from("F-2", "!! 내용이 입력되지 않았습니다. 입력 해 주세요 !!");
		}
		
		ResultData<Integer> writeArticleRd = userArticleService.writeArticle(loginedMemberId, title, body);

		int id = (int) writeArticleRd.getData1();
		
		Article article = userArticleService.getArticle(id);
		
		return ResultData.newData(writeArticleRd, article);
	}

	// list = articles
	@RequestMapping("/user/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		List<Article> articles = userArticleService.getArticles();
		
		return ResultData.from("S-1", "Article List" , articles);
	}

	// Delete
	@RequestMapping("/user/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(int id) {

		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id), id);
		}

		userArticleService.deleteArticle(id);
		return ResultData.from("S-1", Ut.f("%d번 게시물이 삭제되었습니다. :)", id), id);
	}

	// Modify
	@RequestMapping("/user/article/doModify")
	@ResponseBody
	public ResultData<Integer> doModify(int id, String title, String body) {

		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id), id);
		}

		userArticleService.modifyArticle(id, title, body);

		return ResultData.from("S-1", Ut.f("%d번 게시물이 수정되었습니다. :)", id), id);
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
