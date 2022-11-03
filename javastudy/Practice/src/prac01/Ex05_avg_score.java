package prac01;

import java.util.Scanner;

public class Ex05_avg_score {

	public static void main(String[] args) {

		String[] name = { "피카츄", "뽀로로", "브레드" };
		int[] scores = new int[name.length];

		Scanner sc = new Scanner(System.in);

		System.out.println("피카츄의 점수 입력 >>> ");
		System.out.println("뽀로로의 점수 입력 >>> ");
		System.out.println("브레드의 점수 입력 >>> ");

		for (int i = 0; i < name.length; i++) {
			scores[i] = sc.nextInt();
		}

		int total = 0;
		int max = scores[0];
		int min = scores[0];
		int top = 0;
		int bottom = 0;

		for (int i = 0; i < scores.length; i++) {
			total += scores[i];
			
			if (max < scores[i]) {
				max = scores[i];
				top = i;
			}
			if (min > scores[i]) {
				min = scores[i];
				bottom = i;
			}
		}
		double avg = (double) total / scores.length;

		System.out.println("평균 : " + avg + "점");
		System.out.println("1등 : " + max + "점 / 이름 : " + name[top]);
		System.out.println("3등 : " + min + "점 / 이름 : " + name[bottom]);

	}

}
