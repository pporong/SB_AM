package com.gbr.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbr.exam.demo.repository.MemberRepository;
import com.gbr.exam.demo.vo.Member;

@Service
public class UserMemberService {
	@Autowired
	private MemberRepository memberRepository;

	public UserMemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		// loginId 중복 체크
		Member existsMember = getMemberByLoginId(loginId);
		if (existsMember != null) {
			return -1;
		}
		
		// 이름 + 이메일 중복 체크
		existsMember = getMemberByNameAndEmail(name, email);
		if (existsMember != null) {
			return -2;
		}
	
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		return memberRepository.getLastInsertId();
	}
	

	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	
	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}
	
}
