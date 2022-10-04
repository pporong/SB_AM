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
	 LocalDateTime regDate;
	 LocalDateTime updateDate;
	 String loginId;
	 String loginPw;
	 int authlevel;
	 String name;
	 String nickname;
	 String cellphoneNum;
	 String email;
	 boolean delStatus;
	 LocalDateTime delDate;
}
