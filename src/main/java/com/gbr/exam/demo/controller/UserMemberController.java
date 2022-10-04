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

	// 인스턴스변수
	@Autowired
	private UserMemberService userMemberService;

	// join
	@RequestMapping("/user/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {

		if (Ut.empty(loginId)) {
			return ResultData.from("F-1", "!! 아이디를 입력 해 주세요 !!");
		}
		if (Ut.empty(loginPw)) {
			return ResultData.from("F-2", "!! 비밀번호를 입력 해 주세요 !!");
		}
		if (Ut.empty(name)) {
			return ResultData.from("F-3", "!! 이름을 입력 해 주세요 !!");
		}
		if (Ut.empty(nickname)) {
			return ResultData.from("F-4", "!! 닉네임을 입력 해 주세요 !!");
		}
		if (Ut.empty(cellphoneNum)) {
			return ResultData.from("F-5", "!! 전화번호를 입력 해 주세요 !!");
		}
		if (Ut.empty(email)) {
			return ResultData.from("F-6", "!! 이메일을 입력 해 주세요 !!");
		}

		ResultData<Integer> joinRd = userMemberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return (ResultData) joinRd;
		}

		Member member = userMemberService.getMemberById(joinRd.getData1());

		return ResultData.newData(joinRd, member);
	}

	@RequestMapping("/user/member/doLogin")
	@ResponseBody
	public ResultData<Member> doLogin(String loginId, String loginPw) {
		// ID 받기
		if (loginId.length() == 0) {
			return ResultData.from("F-1", "!! 아이디를 입력 해 주세요. !!");

		}

		boolean isLoginIdDup = userMemberService.isLoginIdDup(loginId);

		if (isLoginIdDup == false) {
			return ResultData.from("F-2", Ut.f("!! %s는(은) 존재하지 않는 아이디입니다. !!", loginId));
		}

		Member member = userMemberService.getMemberByLoginId(loginId);

		int tryMaxCount = 3;
		int tryCount = 0;

		while (true) {
			if (tryCount >= tryMaxCount) {
				return ResultData.from("F-3", "!! 비밀번호를 확인하고 다시 시도 해 주세요. !!");
			}
			// PW 받기
			if (loginPw.length() == 0) {
				tryCount++;
				return ResultData.from("F-4", "!! 비밀번호가 입력되지 않았습니다. !!");
			}

			if (member.getLoginPw().equals(loginPw) == false) {
				tryCount++;
				return ResultData.from("F-5", "!! 비밀번호가 일치하지 않습니다. !!");

			}
			return ResultData.from("S-1", Ut.f("!! 반갑습니다. %s님 ! :)", loginId));
		}

	}
}
