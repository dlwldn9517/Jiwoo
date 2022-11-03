package ex07_constructor;

public class ComputerMain {

	public static void main(String[] args) {
										// 인수
		Computer myCom = new Computer("gram", 150);
		myCom.printComputerStatus();

		Computer yourCom = new Computer();
		yourCom.printComputerStatus();
	}

}
