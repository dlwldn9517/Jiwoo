package ex04_throw;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// throw
		// 1. 예외 객체를 만들어서 직접 throw할 수 있다.
		// 2. 자바는 예외로 인식하지 않지만, 실제로는 예외인 경우에 주로 사용한다.
		
		Scanner sc = new Scanner(System.in);
		
		try {
			System.out.println("나이 입력 >>> ");
			String strAge = sc.nextLine();			// 여기서 오류나면 sc.close()하지 못하니 finally 작성
			int age = Integer.parseInt(strAge);
			if(age < 0 || age > 100) {
				throw new RuntimeException("나이는 0 이상 100 이하만 가능합니다.");		// throw가 던진 걸 catch가 받는다. String message는 객체 e에 들어있다.
			}
			System.out.println(age >= 20 ? "성인" : "미성년자");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			sc.close();		// 실제로 finally는 자원을 반납할 때 주로 사용
			System.out.println("finally 블록 실행");
		}
		
	}

}
