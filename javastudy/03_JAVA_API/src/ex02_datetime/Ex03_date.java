package ex02_datetime;

import java.sql.Date;

public class Ex03_date {

	public static void main(String[] args) {
		
		// java.sql.Date 클래스
		// 데이터베이스의 날짜 표시 방식에 맞춰 놓은 클래스
		// Oracle 데이터베이스의 날짜 타입("/" , "-")과 매칭해서 사용
		
		Date now = new Date(System.currentTimeMillis());		// timeStamp 값 넣어달라.
		System.out.println(now);

	}

}
