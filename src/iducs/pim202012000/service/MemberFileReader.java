package iducs.pim202012000.service;

import java.util.Scanner;
import java.util.Vector;

import iducs.pim202012000.model.Member;

import java.io.File;
import java.io.FileNotFoundException;

public class MemberFileReader {
	Scanner sc = null;
	
	public MemberFileReader(File f) throws FileNotFoundException {
		sc = new Scanner(f);
	}
	public Vector<Member> readMember() {
		Vector<Member> retObj = new Vector<Member>();
		while(sc.hasNext()) {
			Member m = new Member();
			String strArr[] = sc.nextLine().split("\t");
			m.setId(Long.parseLong(strArr[0]));
			m.setMid(strArr[1]);
			m.setMpw(strArr[2]);
			m.setMname(strArr[3]);
			m.setEmail(strArr[4]);
			m.setPhone(strArr[5]);
			m.setAddress(strArr[6]);
			retObj.add(m);			
		}
		return retObj;
	}
}
