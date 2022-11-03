package prac01;

public class Ex06_random_game {

	public static void main(String[] args) {
		
		String[] yut = {"", "도", "개", "걸", "윷", "모"};
		
		int total = 0;
		
		while(true) {
			int move = (int)(Math.random() * 5) + 1;
			total += move;
			
			if(move <= 3) {
				System.out.println(yut[move] + ", " + total + "칸 이동한다.");
				break;
			} else {
				System.out.print(yut[move] + ", ");
			}
		}
		
	}

}
