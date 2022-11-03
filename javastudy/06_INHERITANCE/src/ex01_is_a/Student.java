package ex01_is_a;

// 서브클래스
// extends 키워드를 사용
// 슈퍼클래스 Person의 모든 메소드를 자기것처럼 사용

// 끝없이 후손들은 상속받아서 사용할 수 있다.
// 상속이 되는지 안되는지 판단할 때 "extends" 대신에 "is-a"를 넣어보면 알 수 있다.	
			 // 자식
public class Student extends Person {
	public void study() {
		System.out.println("공부한다");
	}

}
