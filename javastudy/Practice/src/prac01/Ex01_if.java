package prac01;

import java.util.Scanner;

public class Ex01_if {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("점수를 입력하세요(0~100) >>> ");
		int score = sc.nextInt();
		
		if(score < 0 || score > 100) {
			System.out.println("잘못된 점수");
			return;
		}
		
		System.out.print("학년을 입력하세요(1~4) >>> ");
		int year = sc.nextInt();
		
		if(year < 1 || year > 4) {
			System.out.println("잘못된 학년");
			return;
		} 
		
		if((year == 4 && score >= 70) || (year < 4 && score >= 60)) {
			System.out.println("합격");
		} else {
			System.out.println("불합격");
		}
		
			
	}

}
