package com.gbr.exam.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.demo.service.UserArticleService;
import com.gbr.exam.demo.util.Ut;
import com.gbr.exam.demo.vo.Article;
import com.gbr.exam.demo.vo.ResultData;
import com.gbr.exam.demo.vo.Rq;

@Controller
public class UserArticleController {

	// 인스턴스 변수
	@Autowired
	private UserArticleService userArticleService;

	// Add = write
	@RequestMapping("/user/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(String title, String body) {
		
		Rq rq = new Rq(req);
		
		// 이미 로그아웃 상태
		if (rq.isLogined() == false) {
			return ResultData.from("F-A", "!! 로그인 후 이용 할 수 있습니다. !!");
		}

		if (Ut.empty(title)) {
			return ResultData.from("F-1", "!! 제목이 입력되지 않았습니다. 입력 해 주세요 !!");
		}
		if (Ut.empty(body)) {
			return ResultData.from("F-2", "!! 내용이 입력되지 않았습니다. 입력 해 주세요 !!");
		}

		ResultData<Integer> writeArticleRd = userArticleService.writeArticle(rq.getLoginedMemberId(), title, body);

		int id = (int) writeArticleRd.getData1();

		Article article = userArticleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		return ResultData.newData(writeArticleRd, "article", article);
	}

	// list = Articles
	@RequestMapping("/user/article/list")
	public String showList(Model model) {
		
		Rq rq = new Rq(req);

		List<Article> articles = userArticleService.getForPrintArticles(rq.getLoginedMemberId());
		
		model.addAttribute("articles", articles);
		
		return "user/article/list";
	}

	// Delete
	@RequestMapping("/user/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Rq rq = new Rq(req);
		
		// 이미 로그아웃 상태
		if (rq.isLogined() == false) {
			return Ut.jsHisoryBack( "!! 로그인 후 이용 해 주세요. !!");
		}
		Article article = userArticleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return Ut.jsHisoryBack(Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id));
		} // 삭제 권한 체크
		if (article.getMemberId() != rq.getLoginedMemberId()) {
			return Ut.jsHisoryBack(Ut.f("!! 삭제 권한이 없습니다. !!"));
		}

		userArticleService.deleteArticle(id);
		return Ut.jsReplace(Ut.f("%d번 게시물이 삭제되었습니다. :)", id), "../article/list");
	}

	// Modify
	@RequestMapping("/user/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {

		Rq rq = new Rq(req);
		
		// 이미 로그아웃 상태
		if (rq.isLogined() == false) {
			return ResultData.from("F-A", "!! 로그인 후 이용 해 주세요. !!");
		}

		Article article = userArticleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("!! %d번 게시물은 존재하지 않습니다. !!", id), "id", id);
		} // 수정 권한 체크
		ResultData actorCanModifyRd = userArticleService.actorCanModify(rq.getLoginedMemberId(), article);

		if (actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}

		return userArticleService.modifyArticle(id, title, body);

	}
	
	// Detail
	@RequestMapping("/user/article/detail")
	public String showDetail(Model model, int id) {
		
		Rq rq = new Rq(req);

		Article article = userArticleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "user/article/detail";
	}

	
}
