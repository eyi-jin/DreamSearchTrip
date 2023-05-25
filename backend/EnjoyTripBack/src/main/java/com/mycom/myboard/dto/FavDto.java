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
public class FavDto {
	private int favId;
	private int contentId;
	private String favName;
	private String favImgUrl;
	private String favAddr;
	private String favTel;
	private String favDate;
	private int userSeq;

}
