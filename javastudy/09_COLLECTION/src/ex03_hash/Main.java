package ex03_hash;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		// 해시 특징

		// 1. 뭘 넣든 비슷한 길이의 알수 없는 난수가 결과로 출력이 된다
		// 2. 글자가 한글자만 바뀌어도 완전히 다른 결과가 출력이 된다
		// 3. 출력값으로 입력값을 예측할 수 없다.
		
		// 자바는 모든 객체를 다르게 보기 때문에 해시코드값이 다르다.
		/*
		Book book1 = new Book();
		System.out.println(book1.hashCode());	// 653305407 - book1 객체의 참조값
		
		Book book2 = new Book();
		System.out.println(book2.hashCode());	// 1227229563 - book2 객체의 참조값
		*/
		
		
		Book book1 = new Book(1, "어린왕자");
		Book book2 = new Book(2, "아몬드");
		Book book3 = new Book(3, "작은아씨들");
		Book book4 = new Book(3, "작은아씨들");
		
		System.out.println(book3.hashCode());
		System.out.println(book4.hashCode());
		
		Set<Book> books = new HashSet<Book>()	;
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);	// 중복 저장 시도(정상 동작하려면 Book 클래스에 hashCode(), equals() 메소드를 오버라이드 해야 함)
		
		for(Book book : books) {
			System.out.println(book);
		}
		
		
	} 

}
