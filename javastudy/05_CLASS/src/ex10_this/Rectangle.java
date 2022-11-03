package ex10_this;

public class Rectangle {

	// 필드
	private int width;
	private int height;
	
	// 생성자
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(int n) {
//		this.width = n;
//		this.height = n;
		this(n, n);	 // 인수 2개인 다른 생성자를 호출한다. ( 바로 위 생성자 이용)
	}
	
	// 메소드
	public int getArea() {
		 return width * height;
	}
	public int getCircumference() {
		return 2 * (width + height);
	}
	
}
