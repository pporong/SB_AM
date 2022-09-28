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
	int authlevel;
	String loginId;
	String loginPw;
	String name;
	String nickname;
	String cellphoneNo;
	String email;
	LocalDateTime regDate;
	LocalDateTime updateDate;
	LocalDateTime delDate;
	int delStatus;
}
