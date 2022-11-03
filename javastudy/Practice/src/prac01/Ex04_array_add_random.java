package prac01;

import java.util.Scanner;

public class Ex04_array_add_random {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("몇 개의 램덤을 생성할까요? >>> ");
		int cnt = sc.nextInt();
		
		if(cnt < 1 || cnt > 100) {
			System.out.println("다음에는 1~100 사이로 입력하세요!");
			return;
		} 

		int arr[] = new int[cnt];
		
		for(int i = 0; i < arr.length; i++) {
			int random = (int)(Math.random() * 100) + 1;
			arr[i] = random;
			for(int j = 0; j < i; j++) {
				if(arr[i] == arr[j]) {
					i--;
				}
			}
			
		}
		
		for(int i = 0; i < arr.length; i++) {
			if(i != 0 && i % 10 == 0)
				System.out.println();
			System.out.print(arr[i] + "\t");
		}
		
	}	

}
