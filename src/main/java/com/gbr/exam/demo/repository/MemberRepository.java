package com.gbr.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.gbr.exam.demo.vo.Member;


@Mapper
public interface MemberRepository {
	
	@Insert(""" 
		INSERT INTO `member`
	    updateDate = NOW(),
	    loginId = #{loginId},
	    loginPw = #{loginPw},
	    `name` = '#{name},
	    nickname = #{nickname},
	    cellphoneNum = #{cellphoneNum},
	    email = #{email};
			""")
	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);
	
	public List<Member> getMembers();
	
	public Member getMember(int id);


}
