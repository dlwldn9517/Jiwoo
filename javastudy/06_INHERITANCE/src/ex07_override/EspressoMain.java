package ex07_override;

public class EspressoMain {

	public static void main(String[] args) {
		
		Espresso espresso = new Espresso();
		espresso.taste();	// 쓰다
		
		Americano americano = new Americano();
		americano.taste();
		
		CafeLatte cafeLatte = new CafeLatte();
		cafeLatte.taste();
		
	}

}
