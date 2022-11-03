package quiz04_employee;

import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Company {
	
	private Employee[] employees;
	private int idx;
	private Scanner sc;
	
	public Company() {
		employees = new Employee[5];
		sc = new Scanner(System.in);
	}

	public void addEmployee() throws EmployeeException {
		if(idx == employees.length) {
			throw new EmployeeException("Full", 1);	// 사원 가득참
		}
		System.out.println("고용 형태 선택 (1.정규 / 2.비정규) >>> ");
		int kind = sc.nextInt();
		
		System.out.println("신규 사원번호 >>> ");
		int empNo = sc.nextInt();
		
		System.out.println("신규 사원명 >>> ");
		String name = sc.next();
		
		switch(kind) {
		case 1:
				System.out.println("기본급 >>> ");
				int salary = sc.nextInt();
				employees[idx++] = new Regular(empNo, name, salary);
				break;
		case 2:
				System.out.println("시급 >>> ");
				double hourPay = sc.nextDouble();
				System.out.println("근무시간 >>> ");
				int workTimes = sc.nextInt();
				Temporary temporary = new Temporary(empNo, name);
				temporary.setHourPay(hourPay);
				temporary.setWorkTimes(workTimes);
				employees[idx++] = temporary;
				break;
		default: throw new EmployeeException("Bad Request", 3);   // 잘못된 요청
		}
		System.out.println("사원 등록 완료. 현재 등록된 사원 " + idx + "명");
	}
	
	public void dropEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		System.out.println("제거할 직원의 사번을 입력하세요. >>> ");
		int empNo = sc.nextInt();
		
		for(int i = 0; i < idx; i++) {
			if(employees[i].getEmpNo() == empNo) {
				System.arraycopy(employees, i + 1, employees, i, idx - 1 - i);
				employees[--idx] = null;
				return;
			}
		}
		System.out.println("사번이 " + empNo + "인 직원이 존재하지 않습니다.");
	}
	
	public void findEmployee() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		System.out.println("검색할 직원의 사번을 입력하세요. >>> ");
		int empNo = sc.nextInt();
		
		for(int i = 0; i < idx; i++) {
			if(employees[i].getEmpNo() == empNo) {
				System.out.println(employees[i]);
				return;
			}
		}
		System.out.println("사번이 " + empNo + "인 직원이 존재하지 않습니다.");
	}
	
	public void printAllEmployees() throws EmployeeException {
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		for(int i = 0; i < idx; i++) {
			System.out.println(employees[i]);
		}
	}
	
	public void manage() {
		while(true) {
			try {
				System.out.print("1.추가 2.삭제 3.조회 4.목록 0.종료 >>>  ");
				int choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1: addEmployee(); break;
				case 2: dropEmployee(); break;
				case 3: findEmployee(); break;
				case 4: printAllEmployees(); break;
				case 0: System.out.println("프로그램을 종료합니다."); return;
				default: System.out.println("존재하지 않는 메뉴입니다.");
				}
			} catch(EmployeeException e) {
				System.out.println(e.getMessage());	
			} catch(InputMismatchException e) {
				sc.next();
				System.out.println("처리 명령은 정수(1 ~ 3, 0)입니다.");
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	
}
