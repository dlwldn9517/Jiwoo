package re_exam;

import java.util.Calendar;
import java.util.Scanner;

public class ReExam {

	public static void main(String[] args) {
		System.out.println("=====문제1=====");
		// q1();
		System.out.println("=====문제2=====");
		// q2();
		System.out.println("=====문제3=====");
		// q3();
		System.out.println("=====문제4=====");
		// q4();
		System.out.println("=====문제5=====");
		// q5();
		System.out.println("=====문제6=====");
		// q6();
		System.out.println("=====문제7=====");
		// q7();
		System.out.println("=====문제8=====");
		//q8();
		System.out.println("=====문제9=====");
		//q9();
	}

	// 문제1. 절대값 구하기
	// 변수 a에 저장된 절대값을 계산하여 변수 abs에 저장하고 출력하시오.
	// 제한조건. 제어문(if, switch)을 사용하지 말고 조건 연산자를 이용하시오.
	// 예시)
	// -5의 절대값은 5입니다.
	public static void q1() {
		int a = -5;
		int abs = (a < 0) ? -a : a;
		System.out.println(a + "의 절대값은 " + abs + "입니다.");
	}

	// 문제2. String strAge에 저장된 나이가 20 이상이면 "성인", 아니면 "미성년자"를 출력하시오.
	// 예시)
	// 25살은 성인입니다.
	public static void q2() {
		String strAge = "25";
		int age = Integer.parseInt(strAge);
		System.out.println(age + "살은 " + (age >= 20 ? "성인" : "미성년자") + "입니다.");
	}

	// 문제3. 너비가 5cm, 높이가 3cm인 삼각형의 넓이를 계산하여 변수 area에 저장한 뒤 출력하시오.
	// 예시)
	// 너비가 5cm, 높이가 3cm인 삼각형의 넓이는 7.5㎠입니다.
	public static void q3() {
		int width = 5;
		int height = 3;
		double area = (width * height * 0.5);
		System.out.println(area);
	}

	// 문제4. 구구단을 외자.
	// 1. 2~9단 사이의 임의의 구구단이 출력된다.
	// 2. 정답을 입력해서 맞으면 "정답", 틀리면 "땡"을 출력하시오.
	// 예시1)
	// 4x9? >>> 36
	// 정답
	// 예시2)
	// 8x7? >>> 49
	// 땡
	public static void q4() {

		int dan = (int) (Math.random() * 8) + 2;
		int n = (int) (Math.random() * 9) + 1;

		Scanner sc = new Scanner(System.in);
		System.out.println(dan + "X" + n + "? >>> ");

		int answer = sc.nextInt();

		System.out.println(dan * n == answer ? "정답" : "땡");

	}

	// 문제5. 변수 month에 저장된 월의 계절과 해당 월이 며칠까지 있는지 출력하시오.
	// 예시)
	// 5월은 31일까지 있고, 봄입니다.
	public static void q5() {
		int month = 11;

		int[] lastday = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String[] seson = { "겨울", "봄", "여름", "가을" };

		System.out.println(month + "월은 " + lastday[month] + "일까지 있고, " + seson[month % 12 / 3] + "입니다.");

	}

	// 문제6. 변수 second에 저장된 초를 시/분/초로 변환하여 출력하시오.
	// 1분이 넘는 경우 '분'까지 표시하고, 1시간이 넘는 경우 '시'까지 표시하시오.
	// 예시1)
	// 40초는 40초입니다.
	// 예시2)
	// 90초는 1분 30초입니다.
	// 예시3)
	// 3690초는 1시간 1분 30초입니다.
	public static void q6() {
		int second = 3690;  // 아래 값으로 바꿔가면서 테스트
		// int second = 90;
		// int second = 3690;

		if (second < 60) {
			System.out.println(second + "초");
		} else if (second < 3600) {
			System.out.println(second / 60 + "분" + (second % 60) + "초");
		} else {
			System.out.println((second / 3600) + "시간" + (second % 3600 / 60) + "분" + (second % 60) + "초");
		}

	}

	// 문제7. 키(height) 정보를 이용해 워터파크에서 탑승이 가능한 모든 어트랙션를 출력하시오.
	// 1. 슬라이드 : 120 이상 탑승 가능
	// 2. 부메랑고 : 130 이상 탑승 가능
	// 3. 인피닛풀 : 140 이상 탑승 가능
	// 예시(키가 125.5인 경우)
	// 슬라이드 탑승 가능합니다.
	public static void q7() {
		String height = "125.5";
		double tall = Double.parseDouble(height);

		if (tall >= 140) {
			System.out.println("인피닛풀 탑승 가능합니다.");
		} else if (tall >= 130) {
			System.out.println("부메랑고 탑승 가능합니다.");
		} else if (tall >= 120) {
			System.out.println("슬라이드 탑승 가능합니다.");
		}

	}

	// 문제8. 두 변수 a, b에 저장된 값 중에서 정수 10과 가까운 값을 출력하시오.
	// 예시)
	// 10과 가까운 정수는 8입니다.
	public static void q8() {
		int a = 8;
		int b = 14;
		int diff1 = (a > 10 ? a - 10 : 10 - a);
		int diff2 = (b > 10 ? b - 10 : 10 - b);
		System.out.println(diff1 < diff2 ? a : b);
	}

	// 문제9. 주민등록번호(personalId)를 분석하여 나이와 성별을 출력하시오.
	// 나이 : 현재년도 - 태어난년도 + 1
	// 성별 : 하이픈(-) 뒤의 글자가 '1', '3', '5'이면 "남자", '2', '4', '6'이면 "여자"
	// 예시)
	// 28살 여자입니다.
	public static void q9() {
		String personalId = "111212-4345678";
		Calendar calendar = Calendar.getInstance();
		int nowYear = calendar.get(Calendar.YEAR);
		String genderNo = personalId.substring(7, 8);
		String birthYear = "";
		switch (genderNo) {
		case "1":
		case "2":
			birthYear = 19 + personalId.substring(0, 2);
			break;
		case "3":
		case "4":
			birthYear = 20 + personalId.substring(0, 2);
			break;
		}
		int age = nowYear - Integer.parseInt(birthYear) + 1;

		String[] gender = { "여자", "남자" };
		System.out.println(age + "살" + gender[Integer.parseInt(genderNo) % 2] + "입니다.");

	}

}
