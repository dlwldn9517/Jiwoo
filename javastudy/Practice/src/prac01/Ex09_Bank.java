package prac01;

public class Ex09_Bank {

	// 필드
	private String accNo;
	private long balance;

	// 생성자
	public Ex09_Bank(String accNo, long balance) {	// Bank b = new Bank("1234", 50000);
		this.accNo = accNo;
		this.balance = balance;
	}

	// 1. 입금(마이너스 입금 불가)
	public void deposit(long money) {	// b.deposit(50000)
		if (money <= 0) {
			return;	  // 반환타입이 void인 메소드를 종료하는 코드
		}
		balance += money;
	}

	// 2. 출금(마이너스 출금 + 잔액보다 큰 출금 불가)
	// 실제로 출금된 금액을 반환
	public long withdrawal(long money) {	// long 출금액 = b.withdrawal(50000);
		if (money <= 0 || money > balance) {
			return 0;
		}
		balance -= money;
		return money;
	}

	// 3. 이체(출금 → 입금)
	public void transfer(Ex09_Bank other, long money) {	// 다른 사람에게 얼마를 보내줄거니?
		// 내 통장에서 출금된 금액만큼 상대 계좌에 입금한다.
		// long withdrawalMoney = withdrawal(money);
		// other.deposit(withdrawalMoney);
		other.deposit(withdrawal(money));
	}

	// 4. 계좌 정보 확인
	public void accInfo() {
		System.out.println("계좌번호 : " + accNo + " / 잔액 : " + balance + "원");
	}

	// 5. main
	public static void main(String[] args) {
		
		Ex09_Bank me = new Ex09_Bank("1234", 50000);
		Ex09_Bank mom = new Ex09_Bank("5678", 100000);
		
		
		me.deposit(5000);
		me.withdrawal(10000);
		me.accInfo();
	}
	
	
	
	
	
	
}
