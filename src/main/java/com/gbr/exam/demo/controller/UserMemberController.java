package com.gbr.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.demo.service.UserMemberService;
import com.gbr.exam.demo.vo.Member;

@Controller
public class UserMemberController {
	
	@Autowired
	private UserMemberService userMemberService;

	private int lastArticleId;
	private List<Member> members;		

	public UserMemberController() {
		lastArticleId = 0;
		members = new ArrayList<>();
	}
	
	// join
	@RequestMapping("/user/member/doJoin")
	@ResponseBody
	public List<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		int id = lastArticleId +1;
		
		userMemberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		return members;
	}

}
