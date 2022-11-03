package ex02_thread;

public class Main {

	public static void main(String[] args) {

		// 스레드 우선순위
		System.out.println("가장 높은 우선 순위 : " + Thread.MAX_PRIORITY);
		System.out.println("가장 낮은 우선 순위 : " + Thread.MIN_PRIORITY);
		System.out.println("보통 우선 순위 : " + Thread.NORM_PRIORITY);
		
		// 스레드 2개(s1, s2)
		Soldier s1 = new Soldier("김상사", new Gun(6));
		Soldier s2 = new Soldier("장병장", new Gun(10));

		// 각 스레드의 우선 순위
		System.out.println("s1 우선 순위 : " + s1.getPriority());
		System.out.println("s2 우선 순위 : " + s2.getPriority());
		
		// 우선 순위가 높은 스레드를 (최대한) 먼저 실행
		// 우선 순위 조정
		s1.setPriority(Thread.MIN_PRIORITY);	// 가장 낮은 우선 순위
		s2.setPriority(Thread.MAX_PRIORITY);	// 가장 높은 우선 순위
		
		// 스레드 실행
		s1.start();	// 김상사가 먼저 쏘지 않을수도 있다.
		s2.start();	
		
	}

}
