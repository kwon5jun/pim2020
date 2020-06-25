package iducs.pim202012000.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TUIView {
	Scanner sc = new Scanner(System.in);
	public void showMenu(boolean isLogin, boolean isRoot) { // 메뉴 보기
		if(isLogin == false) { // 로그인 전
			System.out.print("1. 등록 ");
			System.out.print("2. 로그인 ");
			System.out.print("\n");
		}
		else {
			if(isRoot == false) { // 로그인 후 일반 사용자
				System.out.print("3. 조회 ");
				System.out.print("4. 수정 ");
				System.out.print("5. 탈퇴 ");
				System.out.print("6. 로그아웃 "); // isLogin값 false로 설정	
				System.out.print("\n");
			}
			else { // 관리자
				System.out.print("0. 종료 ");
				System.out.print("6. 로그아웃 ");
				System.out.print("7. 전체목록 "); 
				System.out.print("8. 검색-이름 "); // 지정한 조건으로 검색
				System.out.print("9. 정렬-아이디  "); // 지정한 조건으로 정렬
				System.out.print("\n");
			}
		}		
	}
	public int inputMenu() { // 메뉴 입력
		int menu = 0 ;
		boolean flag = false;
		do {
			try {
				menu = Integer.parseInt(sc.nextLine());
				if(menu < 0 || menu > 11 ) {
					System.out.println("해당 메뉴 번호를 입력하시오.");
				}
				else
					flag = true;
			} catch(InputMismatchException | NumberFormatException ime) {
				System.out.println("숫자 형식을 입력하시오.");
			}
		} while(!flag);
		return  menu;
	}
	
	public String inputMid() {
		System.out.println("아이디를 입력하시오 >>");
		String mid = sc.nextLine();
		return mid;
	}
	public String inputMpw() {
		System.out.println("암호을 입력하시오 >>");
		String mpw = sc.nextLine();
		return mpw;
	}
	public String inputMname() {
		System.out.println("이름을 입력하시오 >>");
		String mname = sc.nextLine();
		return mname;
	}
	public String inputEmail() {
		System.out.println("이메일을 입력하시오(id@induk.ac.kr) >>");
		String email = sc.nextLine();
		return email;
	}
	public String inputPhone() {
		System.out.println("전화번호를 입력하시오(nnn-nnnn-nnnn) >>");
		String phone = sc.nextLine();
		return phone;
	}
	public String inputAddress() {
		System.out.println("주소를 입력하시오 >>");
		String address = sc.nextLine();
		return address;
	}
	
	public String inputFileName() {
		System.out.println("파일이름을 입력하시오 >>");
		String fileName = sc.nextLine();
		return fileName;
	}
	public int showUpdateMenu() {
		int menu = 0 ;
		boolean flag = false;
		do {
			try {
				System.out.println("수정 항목 번호를 입력하시오 >>");
				System.out.print("0. 수정 종료 ");
				System.out.print("1. 이름 ");
				System.out.print("2. 이메일 ");
				System.out.print("3. 연락처");
				System.out.print("4. 주소 "); 
				menu = Integer.parseInt(sc.nextLine());
				if(menu < 0 || menu > 4 ) {
					flag = false;
				}
				else
					flag = true;
			} catch(InputMismatchException | NumberFormatException ime) {
				System.out.println("숫자 형식을 입력하시오.");
			}
		} while(!flag);
		return  menu;
	}
}
