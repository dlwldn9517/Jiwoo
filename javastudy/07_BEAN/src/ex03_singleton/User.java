package ex03_singleton;

public class User {
	
	// static 필드
	// 객체 생성 이전에 메모리에 미리 만들어 두는 필드
	private static User user = new User();		// default 타입
	
	private User() {	// User 객체 생성은 User 내부에서만 가능하다.
		
	}
	
	// static 메소드 (클래스 메소드)
	// 클래스가 생성될 때 함께 생성되는 메소드
	// 객체 생성 이전에 만들어지기 때문에 객체로 접근하지 않는다.
	// 클래스 이름으로 접근한다.
	// User user = User.getInstance(); 오직 이 방법으로만 가져다 사용할 수 있다.
	// 외부에서 new User 만들지 못한다.
	public static User getInstance() {	// User 타입의 user를 받겠다.
		return user;
	}

}
