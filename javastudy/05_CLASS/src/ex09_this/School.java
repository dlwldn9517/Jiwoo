package ex09_this;

public class School {

	// 필드
	private Student[] students;
	private int idx;	// students 배열의 인덱스, students 배열에 저장된 학생수와 같다.
	
	// 생성자
	public School(int cnt) {
		students = new Student[cnt]; // cnt에 2가 대입되서 student[0], student[1]
	}
	
	// add 메소드
	public void addStudent(Student student) {
		if(idx == students.length) {
			System.out.println("Full");
			return;
		}
		students[idx++] = student;
	}
		// 실행하는게 2가지구나 라는 인식이 있다.
		// 동작하는건 동일하다.
//		if(idx == students.length) {
//			System.out.println("Full");
//		} else {
//		students[idx++] = student;
//		}
	
	// 일반 for문 활용해서 출력방법
	public void printStudents() {
//		for(int i = 0; i < idx; i++) {
//			System.out.println(students[i].getName() + "," + students[i].getStuNo());
//		} // 값이 없어도 반복하게 되니까 null값이 반환된다.
//	}

	 //향상 for문 활용해서 출력방법
		for(Student student : students) {
			if(students != null) {
				System.out.println(student.getName() + "," + student.getStuNo());
			}
		}
		
	}
	
}
