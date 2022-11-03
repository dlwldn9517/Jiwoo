package ex04_join;

public class Main {

	public static void main(String[] args) {

		// Calculator를 2개 준비
		// 작업을 반으로 나눠서 진행
		
		// Calculator가 동시에 연산을 수행하려면 Calculator를 스레드로 처리해야 함
		
		Calculator calc1 = new Calculator(1, 50000);
		Thread thread1 = new Thread(calc1);
		thread1.start(); 	// 1번째 계산기 동작 시작
		
		Calculator calc2 = new Calculator(50001, 100000);
		Thread thread2 = new Thread(calc2);
		thread2.start();	// 2번째 계산기 동작 시작
		
		// 모든 계산기의 동작이 끝날때까지 기다린다.
		// join() : 스레드가 종료(die)될 때까지 기다림
		try {
			thread1.join();
			thread1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(thread1.isAlive());
		System.out.println(thread2.isAlive());
		
		System.out.println(calc1.getTotal() + calc2.getTotal());
		// total 값이 0이 나오는 이유?
		// 계산이 안끝났을 때 total은 0이다.
		// main은 계산 시켜놓고 결과값을 받아볼 생각이 없음
		// 계산이 끝나든 말든 난 내 갈 길 가겠다.는 식
		
	}

}
