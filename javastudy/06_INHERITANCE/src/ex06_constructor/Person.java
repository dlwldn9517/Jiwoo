package ex06_constructor;

public class Person {
	
	private String name;
	
	public Person(String name) {
		this.name = name;
	}	// 여기까지 작성하고 getter/setter 실행

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
