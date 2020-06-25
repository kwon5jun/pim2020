package iducs.pim202012000.view;

import java.util.Vector;

import iducs.pim202012000.model.Member;

public class MemberView {
	public void printHeader() {
		System.out.print("이름" + "\t");
		//System.out.print("암호" + "\t");
		System.out.print("이름" + "\t");
		System.out.print("이메일"  + "\t");
		System.out.print("연락처" + "\t");
		System.out.print("주소" + "\n");
	}
	
	public void printList(Vector<Member> vec) {
		printHeader();
		for(Member m : vec) {
			printOne(m);	
		}
	}
	
	public void printOne(Member m) {
		System.out.print(m.getMid() + "\t");
		//System.out.print(m.getMpw() + "\t");
		System.out.print(m.getMname() + "\t");
		System.out.print(m.getEmail() + "\t");
		System.out.print(m.getPhone() + "\t");
		System.out.print(m.getAddress() + "\n");
	}
	
	public void printSuccess(String msg) {
		System.out.println(msg + "작업을 성공하였습니다.");
	}
	public void printSuccess(Member m, String msg) {
		printSuccess(msg);
		printOne(m);
	}
	public void printFail(String msg) {
		System.out.println(msg + "작업을 실패하였습니다.");

	}
}
