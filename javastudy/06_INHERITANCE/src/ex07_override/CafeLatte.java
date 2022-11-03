package ex07_override;

public class CafeLatte extends Espresso {

	private int extraMilk;
	@Override
	public void taste() {
		System.out.println("커피우유맛");
	}
}
