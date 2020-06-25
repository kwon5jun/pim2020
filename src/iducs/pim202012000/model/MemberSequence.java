package iducs.pim202012000.model;

public class MemberSequence {
	long seqId;
	
	public long getSeqId() {
		return seqId;
	}
	public void setSeqId(long seqId) {
		this.seqId = seqId;
	}
	public long next() {
		seqId++;
		return seqId;
	}
}
