package com.gdu.app14.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;

@Component
public class MyFileUtil {

	// 파일명 : UUID(Universally unique identifier)값을 사용, 자바에서 지원
	// 경로명 : 현재 날짜를 디렉터리로 생성해서 사용
	
	public String getFilename(String filename) {
		
		String extension = null;
		
		// 확장자 예외 처리
		if(filename.endsWith("tar.gz")) {
			extension = "tar.gz"; // 리눅스 파일명
			
		} else {
			// unique : 파일이름의 중복을 막기 위해서 사용한다. 마치 비번처럼 이름이 겁나 길어지고 확장자만 살아있다.
			// 원래 올렸던 이름은 DB에 저장되어 있고, 영문 또는 숫자로 변하기 때문에 한글 인코딩 할 필요 없다.
			// 파라미터로 전달된 filename의 확장자만 살려서 UUID.확장자 방식으로 반환
			String[] arr = filename.split("\\.");	// split이 정규식을 받아옴 (. : 모든 글자) 정규식에서 .(마침표) 인식 : \. 또는 [.] 을 기준으로 파일명 분리
			
			// 확장자 (항상 배열의 마지막 요소 length - 1)
			extension = arr[arr.length - 1];
		}
		
		// UUID.확장자
		return UUID.randomUUID().toString().replaceAll("\\-", "") + "." + extension;
		
	}

	
	// 오늘 경로
	public String getTodayPath() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;	// 월은 0 ~ 11이라서 +1 해준다.
		int day = calendar.get(Calendar.DAY_OF_MONTH);	// 해당 월의 일자를 표시해야 해서 DAY_OF_MONTH를 사용
		String sep = Matcher.quoteReplacement(File.separator);	// File.separator는 윈도우의 /나 리눅스의 \을 자동으로 넣어준다.
		return "storage" + sep + year + sep + makeZero(month) + sep + makeZero(day);
	}
	
	// 어제 경로
	public String getYesterdayPath() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);	// 1일 전
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;	// 월은 0 ~ 11이라서 +1 해준다.
		int day = calendar.get(Calendar.DAY_OF_MONTH);	// 해당 월의 일자를 표시해야 해서 DAY_OF_MONTH를 사용
		String sep = Matcher.quoteReplacement(File.separator);
		return "storage" + sep + year + sep + makeZero(month) + sep + makeZero(day);	// storage폴더 밑에 
	}
	
	// 1 ~ 9 → 01 ~ 09
	public String makeZero(int n) {
		return (n < 10) ? "0" + n : "" + n;
	}
	
}
