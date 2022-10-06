package com.gbr.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		}
		if (Ut.empty(body)) {
			return ResultData.from("F-2", "!! 내용이 입력되지 않았습니다. 입력 해 주세요 !!");
		}

		ResultData<Integer> writeArticleRd = userArticleService.writeArticle(loginedMemberId, title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = userArticleService.getArticle(id);

		return ResultData.newData(writeArticleRd, "article", article);
	}

	// list = Articles
	@RequestMapping("/user/article/list")
	public String showList(Model model) {
		List<Article> articles = userArticleService.getArticles();
		
		model.addAttribute("articles", articles);
		
		return "user/article/list";
	}

	// Delete
	@RequestMapping("/user/article/doDelete")
	@ResponseBody
	public ResultData<Integer> doDelete(HttpSession httpsession, int id) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		// 로그인 여부
		if (httpsession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");
		} // 이미 로그아웃 상태
		if (isLogined == false) {
			return ResultData.from("F-A", "!! 로그인 후 이용 해 주세요. !!");
		}
		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id), "id",  id);
		} // 삭제 권한 체크
		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-B", "!! 삭제 권한이 없습니다. !!");
		}

		userArticleService.deleteArticle(id);
		return ResultData.from("S-1", Ut.f("%d번 게시물이 삭제되었습니다. :)", id), "id", id);
	}

	// Modify
	@RequestMapping("/user/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession httpsession, int id, String title, String body) {

		boolean isLogined = false;
		int loginedMemberId = 0;

		// 로그인 여부
		if (httpsession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpsession.getAttribute("loginedMemberId");
		} // 이미 로그아웃 상태
		if (isLogined == false) {
			return ResultData.from("F-A", "!! 로그인 후 이용 해 주세요. !!");
		}

		Article article = userArticleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id), "id", id);
		} // 수정 권한 체크
		ResultData actorCanModifyRd = userArticleService.actorCanModify(loginedMemberId, article);

		if (actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}

		return userArticleService.modifyArticle(id, title, body);

	}

	/*
	 * // Detail
	 * 
	 * @RequestMapping("/user/article/getArticle")
	 * 
	 * @ResponseBody public ResultData<Article> getArticle(int id) { Article article
	 * = userArticleService.getArticle(id);
	 * 
	 * if (article == null) { return ResultData.from("F-1",
	 * Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id)); }
	 * 
	 * return ResultData.from("S-1", Ut.f("%d번 게시물입니다. :)", id), "article",
	 * article); }
	 */
	
	// Detail
	@RequestMapping("/user/article/getArticle")
	public String showDetail(Model model) {
		
		Article article = userArticleService.getArticle(id);
		
		model.addAttribute("id",article);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시물입니다. :)", id), "article", article);
		
		return "user/article/detail";
	}

	
}
