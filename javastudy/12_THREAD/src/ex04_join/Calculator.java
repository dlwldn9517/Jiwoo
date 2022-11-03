package ex04_join;

public class Calculator implements Runnable {
	
	// 필드
	private long total;	// 초기화 안해도 자동으로 0
	private long begin; 
	private long end;
	
	// 생성자
	public Calculator(long begin, long end) {
		this.begin = begin;
		this.end = end;
	}
	
	public void add() {
		for(long n = begin; n <= end; n++) {
			total += n;
		}
	}

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	@Override
	public void run() {
		add();
	}
}
