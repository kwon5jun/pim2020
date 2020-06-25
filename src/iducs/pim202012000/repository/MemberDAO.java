package iducs.pim202012000.repository;

import java.util.Vector;

import iducs.pim202012000.model.Member;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MemberDAO {
	public static long memberId = 1;
	
	public Vector<Member> mVec = null;
	
	Member mdto = null;
	
	public MemberDAO() {
		mVec = new Vector<Member>();		
	}
	
	public Vector<Member> getmVec() {
		return mVec;
	}

	public void setmVec(Vector<Member> mVec) {
		this.mVec = mVec;
	}

	public Vector<Member> readList() {
		return mVec;
	}
	
	@SuppressWarnings("finally")
	public int create(Member dto) {
		int ret = 0;
		try {
			mdto = new Member();
			if(findById(dto.getMid()) == null) { //
				mdto.setMid(dto.getMid());
				mdto.setMpw(dto.getMpw());
				mdto.setMname(dto.getMname());
				mdto.setEmail(dto.getEmail());
				mdto.setPhone(dto.getPhone());
				mdto.setAddress(dto.getAddress());
				mVec.add(mdto);
				ret = 1;
			}
			else
				throw new Exception("오류 : 입력하신 아이디가 존재합니다.");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return ret;
		}
	}
	
	public Member findById(String mid) { // 아
		Member retObj = null;
		System.out.println("findById " + mVec.size());
		for(Member dto : mVec) {
			if(dto.getMid().equals(mid))
				retObj = dto;
		}
		return retObj;
	}
	
	public int login(String id, String pw) {
		int row = 0;
		Member selected = findById(id);
		if(selected != null && pw.equals(selected.getMpw()))
			row = 1;
		return row;
	}

	public int delete(Member mdto) {
		int ret = 0;
		for(Member m : mVec) {
			ret++;
			if(mdto.getMid().equals(m.getMid())) {
				mVec.remove(m);				
			}
		}	
		return ret;
	}

	public int update(Member mdto) {
		// TODO Auto-generated method stub
		int ret = -1;
		for(Member m : mVec) {
			ret++;
			if(mdto.getMid().equals(m.getMid())) {
				mVec.set(ret, mdto);
			}
		}	
		return ret;
	}
	
	/*
	public Vector<Member> selectName(String name) {
		Vector<Member> retObj = new Vector<Member>();
		for(Member m : mVec) {
			if(m.getUname().equals(name))
				retObj.add(m);
		}
		return retObj;
	}

	public int update(Member dto)  {
		int ret = 0;
		Member m;
		for(int i = 0; i < mVec.size(); i++) {
			m = mVec.get(i);
			if(m.getUid().equals(dto.getUid())) {
				m.setUname(dto.getUname());
				mVec.set(i, m);
				ret++;
			}
		}		
		return ret;
	}
	public int delete(String id)  {
		int ret = 0;
		Member m;
		for(int i = 0; i < mVec.size(); i++) {
			m = mVec.get(i);
			if(m.getUid().equals(id)) {
				mVec.remove(i);
				ret++;
			}
		}	
		return ret;
	}

	*/


}
