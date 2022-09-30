package com.gbr.exam.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gbr.exam.demo.service.UserMemberService;
import com.gbr.exam.demo.util.Ut;
import com.gbr.exam.demo.vo.Member;
import com.gbr.exam.demo.vo.ResultData;

@Controller
public class UserMemberController {
	
	@Autowired
	private UserMemberService userMemberService;	
	
	// join
	@RequestMapping("/user/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name,
			String nickname, String cellphoneNum, String email) {
		
		if (Ut.empty(loginId)) {
			return ResultData.from("F-1","!! 아이디를 입력 해 주세요 !!");
		} if (Ut.empty(loginPw)) {
			return ResultData.from("F-1","!! 비밀번호를 입력 해 주세요 !!");
		} if (Ut.empty(name)) {
			return ResultData.from("F-1","!! 이름을 입력 해 주세요 !!");
		} if (Ut.empty(nickname)) {
			return ResultData.from("F-1","!! 닉네임을 입력 해 주세요 !!");
		} if (Ut.empty(cellphoneNum)) {
			return ResultData.from("F-1","!! 전화번호를 입력 해 주세요 !!");
		} if (Ut.empty(email)) {
			return ResultData.from("F-1","!! 이메일을 입력 해 주세요 !!");
		}
		
		int id = userMemberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		// resultCode = S-1
		// msg = 성공!
		// data1 = 비고
		
		if (id == -1) {
			return ResultData.from("F-1", Ut.f("!![%s] 은(는) 이미 사용중인 아이디 입니다. :( !!", loginId));
		}		
		if (id == -2) {
			return ResultData.from("F-1", Ut.f("!! 이미 사용중인 이름 [%s] 입니다. :( !!", name));
		}		
		if (id == -2) {
			return ResultData.from("F-1", Ut.f("!! 이미 사용중인 이메일 [%s] 입니다. :( !!", email));
		}	
			
		Member member = userMemberService.getMemberById(id);
		
		return ResultData.from("S-1", Ut.f("환영합니다! [%s] 님 ! :)", nickname), member);
	}
}

