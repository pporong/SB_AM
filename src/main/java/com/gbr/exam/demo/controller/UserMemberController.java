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
			return ResultData.from("F-2","!! 비밀번호를 입력 해 주세요 !!");
		} if (Ut.empty(name)) {
			return ResultData.from("F-3","!! 이름을 입력 해 주세요 !!");
		} if (Ut.empty(nickname)) {
			return ResultData.from("F-4","!! 닉네임을 입력 해 주세요 !!");
		} if (Ut.empty(cellphoneNum)) {
			return ResultData.from("F-5","!! 전화번호를 입력 해 주세요 !!");
		} if (Ut.empty(email)) {
			return ResultData.from("F-6","!! 이메일을 입력 해 주세요 !!");
		}
		
		// S-1
		// 회원가입이 완료되었습니다.
		// F-1~8
		// 실패
		ResultData joinRd = userMemberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if (joinRd.isFail()) {
			return joinRd;
		}
			
		Member member = userMemberService.getMemberById((int) joinRd.getData1());
		
		return ResultData.newData(joinRd, member);
	}
}

