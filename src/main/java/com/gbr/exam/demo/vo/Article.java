package com.gbr.exam.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	int id;
	String title;
	String body;
	LocalDateTime regDate;
	LocalDateTime updateDate;
}
