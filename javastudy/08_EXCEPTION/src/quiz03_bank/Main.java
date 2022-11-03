package quiz03_bank;

public class Main {

	public static void main(String[] args) {

	Bank me = new Bank("1111", 10_000);
	Bank mom = new Bank("2222", 100_000);
	
	try {
		// me.deposit(-1);;
		mom.transfer(me, 50_000);
	}catch (BankException e) {
		System.out.println(e.getMessage() + " , " + e.getErrorCode());
	}
	
	me.inquiry();
	mom.inquiry();
		
	}
	// try-catch문 사용하지 않고, main도 throws 해도 된다.
	// 그런데 try-catch문 사용하는게 좋다.
}
