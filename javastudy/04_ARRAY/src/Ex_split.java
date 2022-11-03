
public class Ex_split {

	public static void main(String[] args) {
		
		String tel = "010-1234-5678";
		String[] arr = tel.split("-");	// {"010", "1234", "5678"}
		tel = String.join("", arr);		// "01012345678"
		

	}

}
