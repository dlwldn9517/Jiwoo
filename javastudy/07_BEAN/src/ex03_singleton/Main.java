package ex03_singleton;

public class Main {

	public static void main(String[] args) {

		// singleton 객체는 하나만 생성된다.
		// new User를 막는 방법
		// User user = new User(); 이렇게 만들지 못한다.
		
		// 아래 둘은 같은 객체이다.
		User user1 = User.getInstance();
		System.out.println(user1);
		
		User user2 = User.getInstance();
		System.out.println(user2);
		
		// User user = new User(); 외부에서는 새로운 객체를 생성할 수 없다.
		
	}

}
