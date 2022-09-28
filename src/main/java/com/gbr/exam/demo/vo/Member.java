package com.gbr.exam.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	int id;
	String loginId;
	String loginPw;
	String name;
	String nickname;
	String cellphoneNo;
	String email;
	LocalDateTime regDate;
}
