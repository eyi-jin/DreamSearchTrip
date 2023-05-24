package com.mycom.myboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
	private int todoId;
	private String todoContent;
	private int todoStatus;
	private int userSeq;
}
