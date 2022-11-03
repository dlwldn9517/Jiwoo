package prac03;

import java.util.Arrays;
import java.util.Scanner;

public class Hall {
	
	private String hallName;
	private SeatGroup[] seatGroups;
	private Scanner sc;
	private String[] seatTypes = {"S", "R", "A"};
	private int[] seatCount = {10, 20, 30};
	
	public Hall(String hallName) {
		this.hallName = hallName;
		seatGroups = new SeatGroup[seatTypes.length];
		for(int i = 0; i < seatGroups.length; i++) {
			seatGroups[i] = new SeatGroup(seatTypes[i], seatCount[i]);
		}
		sc = new Scanner(System.in);
	}
	
	// 예약
	public void reserve() {
		System.out.print("예약할 좌석 타입 입력 " + Arrays.toString(seatTypes) + " >>> ");
		String seatType = sc.next();
		for(int i = 0; i < seatTypes.length; i++) {
			if(seatType.equalsIgnoreCase(seatTypes[i])) {
				seatGroups[i].reserve();
				return;
			}
		}
		System.out.println(seatType + "타입은 없는 타입입니다.");
	}
	
	// 예약 취소
	public void cancel() {
		System.out.print("취소할 좌석 타입 입력 " + Arrays.toString(seatTypes) + " >>> ");
		String seatType = sc.next();
		for(int i = 0; i < seatTypes.length; i++) {
			if(seatType.equalsIgnoreCase(seatTypes[i])) {
				seatGroups[i].cancel();
				return;
			}
		}
		System.out.println(seatType + "타입은 없는 타입입니다.");
	}
	
	// 전체 예약 확인
	public void hallInfo() {
		System.out.println("[" + hallName + "]");
		for(int i = 0; i < seatGroups.length; i++) {
			seatGroups[i].reserveInfo();
		}
		System.out.println();
	}
	
	// 실행
	public void manage() {
		while(true) {
			System.out.print("1.예약 2.취소 3.예약현황 0.종료 >>> ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1: reserve(); break;
			case 2: cancel(); break;
			case 3: hallInfo(); break;
			case 0: return;
			default: System.out.println("Bad Request");
			}
		}
	}
	

}
