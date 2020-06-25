package iducs.pim202012000.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import iducs.pim202012000.model.Member;

public class MemberFileWriter {
	BufferedWriter bw = null;
	FileWriter fw = null;
	public MemberFileWriter(File f) throws IOException {
		fw = new FileWriter(f);
		//bw = new BufferedWriter(fw);
	}
	public void saveMember(Vector<Member> mVec) throws IOException {
		System.out.println(mVec.size());
		for(Member m : mVec) {
			try {
				fw.write(m.getId() + "\t");
				fw.write(m.getMid() + "\t");
				fw.write(m.getMpw() + "\t");
				fw.write(m.getMname() + "\t");
				fw.write(m.getEmail() + "\t");
				fw.write(m.getPhone() + "\t");
				fw.write(m.getAddress() + "\n");
				//fw.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		fw.close();		
	}
}
