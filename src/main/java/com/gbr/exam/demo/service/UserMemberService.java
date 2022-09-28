package com.gbr.exam.demo.service;

import org.springframework.stereotype.Service;

import com.gbr.exam.demo.repository.MemberRepository;

@Service
public class UserMemberService {
	private MemberRepository memberRepository;
	
	public UserMemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
		
	}
		
	public void join (String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
	}
	
}
