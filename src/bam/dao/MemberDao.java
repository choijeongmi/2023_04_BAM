package bam.dao;

import java.util.ArrayList;
import java.util.List;

import Util.Util;
import bam.dto.Member;

public class MemberDao extends Dao {

	private List<Member> members;
	
<<<<<<< HEAD
	//
=======
	
>>>>>>> 22d1f2dba577cb01243d2476485f692e51bd55f6
	public MemberDao() {
		this.members = new ArrayList<>();
		
	}
<<<<<<< HEAD
	
	public String getWriterName(int memberId) {

		for (Member member : members) {
			if (memberId == member.id) {
				return member.name;
			}
		}
		return null;
	}
=======
>>>>>>> 22d1f2dba577cb01243d2476485f692e51bd55f6



	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	public void add(Member member) {
		members.add(member);
		lastId++;

	}

	public boolean isLoginIdDup(String loginId) {
		Member member = getMemberByLoginId(loginId);
		if (member != null) {
			return false;

		}
		return true;

	}

	public void makeTestData() {
		for (int i = 1; i <= 3; i++) {

			String loginId = "test" + i;
			String loginPw = "test" + i;
			String name = "사용자" + i;

			Member member = new Member(setLastId(), loginId, loginPw, name, Util.getDateStr());
			add(member);

		}

	}

}
