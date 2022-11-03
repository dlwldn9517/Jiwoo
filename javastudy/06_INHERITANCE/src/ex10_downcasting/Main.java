package ex10_downcasting;

public class Main {

	public static void main(String[] args) {

		// 클래스 타입 : Person
		// 객체(인스턴스) : p
		Person p = new Alba();		// 업캐스팅
		
		// instanceof 연산자
		// 특정 인스턴스가 어떤 클래스 타입인지 점검하는 연산자
		// 해당 클래스 타입이면 true 반환, 아니면 false 반환
		System.out.println(p instanceof Person);
		System.out.println(p instanceof Student);
		System.out.println(p instanceof Alba);
		
		// p가 Student타입의 인스턴스이면 study() 메소드를 호출할 수 있다.
		if(p instanceof Student) {
			// 다운캐스팅 p.study() 자동 완성으로 작성
			((Student) p).study();		
		}	// 공부한다
		
		// p가 Alba타입의 인스턴스이면 work() 메소드를 호출할 수 있다.
		if(p instanceof Alba) {	
			// 다운캐스팅 p.work() 자동 완성으로 작성
			((Alba) p).work();
		}	// 일한다
	}

}
