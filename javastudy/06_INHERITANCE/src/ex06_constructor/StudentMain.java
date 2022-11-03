package ex06_constructor;

public class StudentMain {

	public static void main(String[] args) {
		
		Student student = new Student("tom", "goodee");
		
		System.out.println(student.getName());		// tom
		System.out.println(student.getSchool());	// goodee
		
	}

}
