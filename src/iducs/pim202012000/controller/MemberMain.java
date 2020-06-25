package iducs.pim202012000.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import iducs.pim202012000.service.MemberServiceImpl;
import iducs.pim202012000.view.MemberView;
import iducs.pim202012000.view.TUIView;

public class MemberMain {

	public static Map<String, String> session = new HashMap<String, String>();
	public static TUIView tui = new TUIView();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberServiceImpl mSvr = new MemberServiceImpl();

		MemberView memberView = new MemberView();
		
		boolean isLogin = false;
		boolean isRoot = false;
		
		mSvr.readFile();
		int menu = 0;
		do {
			if(session.get("id") != null) {
				isLogin = true;
				if(session.get("id").toString().equals("root"))
					isRoot = true;
			}
			else {
				isLogin = false;
				isRoot = false;
			}
			
			tui.showMenu(isLogin, isRoot);
			menu = tui.inputMenu();
			switch(menu) {
				case 0: // 종료  : 로그아웃, 파일로 저장
					mSvr.logout();
					mSvr.saveFile();
					break;
				case 1: // 등록
					mSvr.register();
					mSvr.applyUpdate();
					break;
				case 2: // 로그인
					mSvr.login(tui.inputMid(), tui.inputMpw());
					break;
				case 3: // 조회
					memberView.printOne(mSvr.readInfo(session.get("id").toString()));
					break;
				case 4: // 수정
					if(mSvr.updateInfo(session.get("id").toString()) != null)
						memberView.printOne(mSvr.readInfo(session.get("id").toString()));
					else
						memberView.printFail("업데이트 실패");
					break;
				case 5: // 탈퇴
					if(mSvr.deleteInfo() > 0)
						memberView.printSuccess("삭제 성공");
					else
						memberView.printFail("삭제 실패");
					break;
				case 6: // 로그아웃
					mSvr.logout();
					break;
				case 7:	// 전체 목록
					memberView.printList(mSvr.readList());
					break;
				case 8:	// 검색 - 이름
					mSvr.findByName();					
					break;
				case 9:	// 정렬 - 아이디					
					mSvr.sortById();
					break;
			}
		} while(menu != 0);		
		
	}
}
