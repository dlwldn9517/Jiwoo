package quiz08_schedule;

import java.util.Scanner;

public class WeekScheduler {
	
	private int nthWeek;	// 1 ~ n주차
	private Day[] week;
	private String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};
	private Scanner sc;
	
	public WeekScheduler(int nthWeek) {
		this.nthWeek = nthWeek;
		week = new Day[7];
		sc = new Scanner(System.in);
	}
	
	private void makeSchedule() {
		System.out.println("◈◈◈◈◈ 등록 ◈◈◈◈◈");
		System.out.println("요일 입력 >>> ");
		String dayName = sc.next().substring(0, 1);	// "월요일"이라고 해도 "월"만 사용
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {		// private Day[] week;의 길이
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {	// 등록된 스케쥴이 없으면
					System.out.print("스케쥴 입력 >>> ");
					String schedule = sc.nextLine();	// 스케쥴에 공백 입력이 가능
					Day day = new Day();
					day.setSchedule(schedule);
					week[i] = day;
					System.out.println(dayName + "요일에 새 스케쥴이 등록 되었습니다.");
				} else {
					System.out.println(dayName + "요일은 이미 스케쥴이 있습니다.");
				}
				return;	// if-else 이후에는 끝
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");	// "최요일" 처럼 잘못된 요일 입력할 경우
	}
	
	private void changeSchedule() {
		System.out.println("◈◈◈◈◈ 변경 ◈◈◈◈◈");
		System.out.print("변경할 요일 입력 >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 스케쥴이 없습니다.");
					System.out.print("새 스케쥴을 등록할까요?(y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						System.out.print("새 스케쥴 입력 >>> ");
						String schedule = sc.nextLine();
						Day day = new Day();
						day.setSchedule(schedule);
						week[i] = day;
						System.out.println(dayName + "요일에 새 스케쥴이 등록 되었습니다.");
					} else {
						System.out.println("스케쥴 변경이 취소 되었습니다.");
					}
				} else {
					System.out.println(dayName + "요일의 스케쥴은 " + week[i].getSchedule() + "입니다.");
					System.out.print("변경할까요?(y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						System.out.print("변경할 스케쥴 입력 >>> ");
						String schedule = sc.nextLine();
						week[i].setSchedule(schedule);
						System.out.println(dayName + "요일의 스케쥴이 변경 되었습니다.");
					} else {
						System.out.println("스케쥴 변경이 취소 되었습니다.");
					}
				}
				return;
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");	// "최요일" 처럼 잘못된 요일 입력할 경우
	}
	
	private void deleteSchedule() {
		System.out.println("◈◈◈◈◈ 삭제 ◈◈◈◈◈");
		System.out.print("삭제할 요일 입력 >>> ");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 스케쥴이 없습니다.");
				} else {
					System.out.println(dayName + "요일의 스케쥴은 " + week[i].getSchedule() + "입니다.");
					System.out.print("삭제할까요?(y/n) >>> ");
					String yesNo = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo.equalsIgnoreCase("y")) {
						week[i] = null;
						System.out.println(dayName + "요일의 스케쥴이 삭제 되었습니다.");
					} else {
						System.out.println("스케쥴 삭제가 취소 되었습니다.");
					}
				}
				return;
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다.");	// "최요일" 처럼 잘못된 요일 입력할 경우
	}
	
	private void printWeekSchedule() {
		System.out.println("◈◈◈◈◈ 전체조회 ◈◈◈◈◈");
		System.out.println(nthWeek + "주차 스케쥴 안내");
		for(int i = 0; i < week.length; i++) {
			System.out.print(dayNames[i] + "요일 - ");
			System.out.println(week[i] == null ? "X" : week[i].getSchedule());
		}
	}
	
	public void manage() {
		while(true) {
			System.out.print("1.등록 2.변경 3.삭제 4.전체조회 0.종료 >>> ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice) {
			case 1: makeSchedule(); break;
			case 2: changeSchedule(); break;
			case 3: deleteSchedule(); break;
			case 4: printWeekSchedule(); break;
			case 0 : System.out.println("스케쥴러를 종료합니다."); return;
			default: System.out.println("인식할 수 없는 명령입니다.");
			}
		}
	}
	
}
