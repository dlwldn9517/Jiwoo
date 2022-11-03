package ex07_override;

public class Espresso extends Coffee {
	
	// 메소드 오버라이딩
	// 유일한 규칙은 똑같은 모양으로 만들면 된다.
	// 권장 규칙은 @Override 추가
	
	@Override
	public void taste() {
		System.out.println("쓰다");
	}
}
