package prac01;

import java.util.Scanner;

public class Ex02_switch {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("무슨 커피 드릴까요? >>> ");
		String menu = sc.next();
		int price = 0;
		
		switch(menu) {
		case "아메리카노": price = 2000; break;
		case "에스프레소":
		case "카푸치노":
		case "카페라떼": price = 3500; break;
		default: System.out.println(menu + "는 메뉴에 없습니다.");
		}
		if(price != 0) {
			System.out.println(menu + "는 " + price + "원입니다.");
			
		sc.close();
		}

	}

}
