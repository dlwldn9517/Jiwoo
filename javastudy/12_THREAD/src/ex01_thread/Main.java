package ex01_thread;

public class Main {

	public static void main(String[] args) {

		System.out.println("main 시작");
		
		Process process1 = new Process("연산");
		process1.start();	// Process 클래스의 오버라이드된 run() 메소드가 호출
		
		Process process2 = new Process("제어");
		process2.start();	// 제어가 먼저 시작할 수도 있다. 시작하세요 하면 바로 시작 안할 수 있다.
							// 스케줄러에 의해서 늦게 시작할 수도 있다.
		
		System.out.println("main 종료");
		// 메인은 프로세스한테 너 시작해 알려주고 내려와서 종료함
		// 스레드는 특정 시작을 알려주면 그 때 시작
	}

}
