package ex03_string;

public class Ex02_StringBuilder {

	public static void main(String[] args) {
		
		// java.lang.StringBuilder 클래스
		
		StringBuilder sb = new StringBuilder();
		
		// append : + , 그냥 더해준다.
		sb.append(1);
		sb.append(true);
		sb.append('T');
		sb.append(3.14);
		sb.append("hello");
		
		System.out.println(sb);		// 1trueT3.14hello
		
		
		// 동등 비교
		System.out.println(sb.equals("1trueT3.14hello"));	// false
		// WHY? sb는 String이 아니고, StringBuilder이기 때문에
		
		// StringBuilder로 만든 문자열은 반드시 마지막에 String으로 변환해야 함
		String result = sb.toString();
		System.out.println(result.equals("1trueT3.14hello"));	// true
		
		// StringBuilder 사용하는 이유는 성능 떄문이다.
		
		
		// String과 StringBuilder의 성능 테스트
		// abcdefghijklmnopqrstuvwxyz
		// StringBuilder가 더 빠르다.
		
		
		String alphabet1 = "";
		long begin1 = System.nanoTime();
		
		for(char ch = 'a'; ch <= 'z'; ch++) {
			alphabet1 += ch;
		}
		long end1 = System.nanoTime();
		System.out.println((end1 - begin1) + alphabet1);
		
		

		StringBuilder sb2 = new StringBuilder();
		long begin2 = System.nanoTime();
		for(char ch = 'a'; ch <= 'z'; ch++)	{
			sb2.append(ch);
		}
		long end2 = System.nanoTime();
		String alphabet2 = sb2.toString();
		System.out.println((end2 - begin2) + alphabet2);
		
		
		// 연습. 대문자 6자리로 구성된 인증코드 작성하기
		StringBuilder sbCode = new StringBuilder();
		
		for(int n = 0; n < 6; n++) {
			sbCode.append((char)((int)(Math.random() * 26) + 'A'));
		}
		String code = sbCode.toString();
		System.out.println("인증코드 : " + code);
		
		
		
		// 연습. 1 2 3 4 5 6 7 8 9 10 만들기
		StringBuilder sbPaging = new StringBuilder();
		for(int n = 1; n <= 10; n++)	{
			sbPaging.append(n + " ");
		}
		String paging = sbPaging.toString();
		System.out.print(paging);
		

	}

}
