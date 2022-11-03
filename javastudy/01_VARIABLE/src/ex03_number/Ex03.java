package ex03_number;

public class Ex03 {

	public static void main(String[] args) {
		
		// 대입 연산
		int score = 100;	// 등호(=)가 대입 연산자이다.
		System.out.println(score);
	
		
		System.out.println("-------------");
		// 연습
		// x에 10이 있고, y에 20이 있다.
		// x와 y에 저장된 값을 서로 교환하시오.
		
		int x = 10;
		int y = 20;
		int temp;
		
		temp = x;
		x = y;
		y = temp;
		
		System.out.println(x);
		System.out.println(y);
		
		
		System.out.println("-------------");
		// 복합 대입 연산자
		// +=, -=, *=, /=, %= 등
		int wallet = 0;
		wallet = wallet + 5000;
		wallet += 5000;		// wallet = wallet + 5000;
		wallet -= 3000;		// wallet = wallet - 3000;
		System.out.println(wallet);
		
		
		System.out.println("-------------");
		// 연습
		// 통장 잔액(balance)에서 이자 5%를 받았음을 나타내자.
		long balance = 10000;													// 실무에서 이렇게 사용X (소수점을 미리 털어내면 X)
		balance *= 1.05;		// balance를 double로 Promotion해서 처리한다.
		System.out.println(balance);
		
		balance *= 1.05;	// 1.05 double 타입이 자동 변환(Promotion) 됨.
		// balance = balance * 1.05; 실패. balance * 1.05 결과는 double이기 때문에 long balance에 저장할 수 없다.
		// balance = (long)(balance * 1.05); 성공. balance * 1.05 결과를 long으로 casting해서 저장할 수 있다.
		
		
		// balance = balance + balance * 0.05; 실패. 결과가 double 이라서 long balance에 저장할 수 없다.
		// balance = (long)(balance + balance * 0.05); 성공. balance + balance * 0.05 결과를 long으로 casting해서 저장할 수 있다.
		
	}

}
