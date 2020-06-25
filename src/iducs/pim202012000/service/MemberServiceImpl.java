package iducs.pim202012000.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

import iducs.pim202012000.controller.MemberMain;
import iducs.pim202012000.model.Member;
import iducs.pim202012000.repository.MemberDAO;
import iducs.pim202012000.view.MemberView;

public class MemberServiceImpl {
	MemberDAO mDao = null;	
	MemberView mView = null;
	Member mdto = null;
	
	public MemberServiceImpl(){
		mDao = new MemberDAO();
		mView = new MemberView();
	}	

	public void register() { // 회원 등록 - 정보 생성
		mdto = new Member();
		mdto.setMid(MemberMain.tui.inputMid());
		mdto.setMpw(MemberMain.tui.inputMpw());
		mdto.setMname(MemberMain.tui.inputMname());
		mdto.setEmail(MemberMain.tui.inputEmail());
		mdto.setPhone(MemberMain.tui.inputPhone());
		mdto.setAddress(MemberMain.tui.inputAddress());
		
		while(!isValid(mdto))
			;
		
		if(mDao.create(mdto) >= 1)
			mView.printSuccess("회원 정보 생성");
		else
			mView.printFail("회원 정보 생성");
		
	}
	
	public boolean login(String mid, String mpw) {
		boolean isLogin = false;
		if(mDao.login(mid, mpw) >= 1) {
			mView.printSuccess("로그인 성공");
			isLogin = true;
			if(mid.equals("root")) {				
				MemberMain.session.put("id", mid);
			}
			else {
				MemberMain.session.put("id", mid);
			}
		}
		else {
			MemberMain.session.put("id", null);
			mView.printFail("로그인 실패");
		}
		return isLogin;
	}
	
	private boolean isValid(Member dto) {
		boolean isValid = true;
		if(dto.getMid().length() == 0) {
			isValid = false;
			mdto.setMid(MemberMain.tui.inputMid());
		}
		if(dto.getMpw().length() == 0)
			mdto.setMid(MemberMain.tui.inputMpw());
		if(dto.getMname().length() == 0)
			mdto.setMid(MemberMain.tui.inputMname());
		if(dto.getEmail().length() == 0 || !dto.getEmail().contains(new StringBuffer("@"))) { // 이메일 패턴 확인
			isValid = false;
			mdto.setEmail(MemberMain.tui.inputEmail());
		}
		// 전화번호 패턴 확인
		return isValid;
	}
	
	public Member readInfo(String mid) {
		return mDao.findById(mid);
	}
	
	public Member updateInfo(String mid) {
		Member mdto = mDao.findById(MemberMain.session.get("id").toString());
		int menu = 0;
		do {
			menu = MemberMain.tui.showUpdateMenu();
			switch(menu) {
			case 0: // 수정 종료
				break;
			case 1: // 이름 수정
				mdto.setMname(MemberMain.tui.inputMname());
				break;
			case 2: // 이메일 수정
				mdto.setEmail(MemberMain.tui.inputEmail());
				break;
			case 3: // 연락처 수정
				mdto.setPhone(MemberMain.tui.inputPhone());
				break;
			case 4: // 주소 수정
				mdto.setAddress(MemberMain.tui.inputAddress());
				break;
			}
		} while(menu != 0);
		
		if(mDao.update(mdto) < 0) // 적용
			mdto = null;
		
		return mdto;		
	}
	
	public int deleteInfo() { // 회원 탈퇴, 암호 확인 후 삭제 (추가)
		Member mdto = mDao.findById(MemberMain.session.get("id").toString());
		return mDao.delete(mdto);
	}
	
	public void logout() {
		MemberMain.session.remove("id");
	}
	
	public void findByName() {
		// TODO Auto-generated method stub
		
	}

	public void sortById() {
		// TODO Auto-generated method stub
		
	}
	
	public Vector<Member> readList() { 
		return mDao.getmVec();
	}
	
	
	public Vector<Member> readList(int page, int perCount) { 
		return null; // 수정해야 함
	}

	public void readFile() { // 파일을 읽어서 member list 생성
		File file = new File("ab202012000.txt");
		if(file.canRead()) {			
			try {
				MemberFileReader mfr = new MemberFileReader(file);
				mDao.setmVec(mfr.readMember());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	

	public void saveFile() { // member list를 파일로 저장
		File file = new File("ab202012000.txt");
			try {
				MemberFileWriter mfw = new MemberFileWriter(file);
				mfw.saveMember(mDao.getmVec());
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void applyUpdate() {
		saveFile();
		readFile();
	}


}
