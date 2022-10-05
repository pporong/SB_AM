package com.gbr.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbr.exam.demo.repository.MemberRepository;
import com.gbr.exam.demo.util.Ut;
import com.gbr.exam.demo.vo.Member;
import com.gbr.exam.demo.vo.ResultData;

@Service
public class UserMemberService {
	@Autowired
	private MemberRepository memberRepository;

	public UserMemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	// join
	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		// loginId 중복 체크
		Member existsMember = getMemberByLoginId(loginId);
		if (existsMember != null) {
			return ResultData.from("F-7", Ut.f("!![%s] 은(는) 이미 사용중인 아이디 입니다. :( !!", loginId));
		}
		
		// 이름 + 이메일 중복 체크
		existsMember = getMemberByNameAndEmail(name, email);
		
		if (existsMember != null) {
			return ResultData.from("F-8", Ut.f("!! 이미 사용중인 이름 [%s]과 이메일 [%s] 입니다. :( !!", name, email));
		}
	
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1", "환영합니다!! :)", "id", id);
	}
	

	// login
	public void login(String loginId, String loginPw) {
		
		
	}
	

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	
	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public boolean isLoginIdDup(String loginId) {
		return memberRepository.isLoginIdDup(loginId);
	}
	

}
