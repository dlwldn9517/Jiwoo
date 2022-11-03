package prac01;

public class Watch {

	private int hour;	// 16
	private int minute;	// 15
	private int second;	// 30
	
	public Watch(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public void addHour(int hour) {
		if(hour < 0)
			return;
		this.hour += hour;
		this.hour %= 24;
	}
	
	public void addMinute(int minute) {
		if(minute < 0)
			return;
		this.minute += minute;	// 15+61 = 76
		addHour(this.minute / 60); // 76/60 = 1시간 더하기
		this.minute %= 60;
	}
	
	public void addSecond(int second) {
		if(second < 0)
			return;	
		this.second += second;	// 30+3661 = 3691
		addMinute(this.second / 60);  // 3691/60 = 1시간 1분 1초
		this.second %= 60;
	}
	
	public void see() {
		System.out.println(hour + "시 " + minute + "분 " + second + "초");
	}
	
}
