package ex09_this;

public class SchoolMain {

	public static void main(String[] args) {
		
		Student student1 = new Student();
		
		student1.setStuNo("11025");
		student1.setName("한소희");
		
		Student student2 = new Student("11026", "안보현");
	
		// School 클래스의 생성자 cnt에 2가 들어간다.
		School school = new School(2);
		// School 클래스의 add 메소드 student에 student1과 student2가 들어간다.
		
		school.addStudent(student1);
		school.addStudent(student2);
		school.printStudents();
		
	}

}
