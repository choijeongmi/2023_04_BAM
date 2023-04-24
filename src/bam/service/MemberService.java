package bam.service;

import bam.dao.MemberDao;
import bam.dto.Member;

public class MemberService {

	private MemberDao memberDao;

	public MemberService() {
		this.memberDao = new MemberDao();
	}
<<<<<<< HEAD
//
=======

>>>>>>> 22d1f2dba577cb01243d2476485f692e51bd55f6
	public int setLastId() {
		return memberDao.setLastId();
	}

	public void add(Member member) {
		memberDao.add(member);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public boolean isLoginIdDup(String loginId) {
		return memberDao.isLoginIdDup(loginId);
	}

	public void makeTestData() {
		memberDao.makeTestData();
	}
<<<<<<< HEAD
	public String getWriterName(int memberId) {
		return memberDao.getWriterName(memberId);
	}
=======
	
>>>>>>> 22d1f2dba577cb01243d2476485f692e51bd55f6
	
	
	
	

}
