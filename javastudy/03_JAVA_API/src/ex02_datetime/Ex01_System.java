package ex02_datetime;

public class Ex01_System {

	public static void main(String[] args) {
		
		// 1. 타임스탬프 (timeStamp) *****얘는 중요함
		// 1970-01-01 0:00부터 1/1000초마다 증가하는 long 타입의 정수값
		long timestamp = System.currentTimeMillis();
		System.out.println(timestamp);
		// 파일이름으로 하면, 중복 회피 수단으로 사용가능 (계속 숫자가 늘어나니까)
		
		
		// 2. 나노타임 (nanoTime) 중요X
		// s > ㎳(밀리, 천분의 1초) > ㎲(나노, 백만분의 1초) > ㎱(나이크로?, 십억분의 1초)
		// 나노초(㎱) 값을 가지는 long 타입의 정수값
		// 어떤 기준일자는 없어서 두 개의 나노초(㎱) 사이의 경과시간 계산용
		
		long beginTime = System.nanoTime();
		int total = 1 + 2 + 3 + 4 + 5;
		long endTime = System.nanoTime();
		
		System.out.println(total + "계산에 걸린 시간 : " + (endTime - beginTime) + "㎱");

	}

}
