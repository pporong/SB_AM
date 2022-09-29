package com.gbr.exam.demo.controller;

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
	
	// join
	@RequestMapping("/user/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		int id = userMemberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if(id == -1) {
			return "이미 사용중인 아이디 입니다. :( ";
		}
				
		Member member = userMemberService.getMemberById(id);
		
		return member;
	}

}
