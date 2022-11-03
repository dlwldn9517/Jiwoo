package ex06_constructor;

// default 형식이 아니라서 빨리 슈퍼클래스의 생성자를 호출하라고 오류가 뜬다.
public class Student extends Person {	

	private String school;
	
	public Student(String name, String school) {
		super(name);	// 슈퍼클래스의 생성자인 Person을 "super"라고 부른다.
		this.school = school;
	}	// 여기까지 작성하고 getter/setter 실행

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
}
