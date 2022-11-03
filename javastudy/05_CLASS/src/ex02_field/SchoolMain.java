package ex02_field;

public class SchoolMain {

	public static void main(String[] args) {
		
		School school = new School();
		school.name = "바다중학교";
		
		Student student1 = new Student();
		student1.stuNo = "11025";
		student1.name = "손석구";
		
		Student student2 = new Student();
		student2.stuNo = "11026";
		student2.name = "박은빈";
		
		school.students[0] = student1;
		school.students[1] = student2;
				
		for(int i = 0; i < school.students.length; i++) {
			System.out.println(school.students[i].stuNo); 
			System.out.println(school.students[i].name); 
		}
		
/*				|--------|
	  students  | 0x123  |
				|--------|
				|--------|
	students[0]	| 0x1111 | 0x123
	students[1]	| 0x2222 |
				|--------|
				|--------|
	   student1 | 0x1111 |
				|--------|
	   student2	| 0x2222 |
				|--------|
				|--------|
				| 11025	 | 0x1111
				| 손석구 |
				|--------|
				| 11026  | 0x2222
				| 박은빈 |
				|--------|
				|		 |

*/		
		
	}

}
