package com.gdu.mysql.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UploadDTO {
	private int uploadNo;
	private String title;
	private String content;
	private Date createDate;	// sql, util 둘다 되는데 sql은 시간이 안나옴
	private Date modifyDate;	// util로 했을 경우 포맷해줘야 함
	private int attachCnt;
}
