package bam.controller;

import java.util.List;
import java.util.Scanner;

import Util.Util;
import bam.dto.Member;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private int lastMemberId;
	

	public MemberController(List<Member> members, Scanner sc) {
		this.members = members;
		this.sc = sc;
		this.lastMemberId = 0;
	}

	@Override
	public void doAtcion(String cmd, String methodName) {

		switch (methodName) {
		case "join":
			if(LoginedMember != null) {
				System.out.println("로그아웃 후 이용해주세요");
				return;
			}
			dojoin();

			break;

		case "login":
			if(LoginedMember != null) {
				System.out.println("로그아웃 후 이용해주세요");
				return;
			}
			dologin();

			break;
		case "logout":
			if(LoginedMember == null) {
				System.out.println("로그인 상태가 아닙니다.");
				return;
			}
			dologout();
			
			break;

		default:
			System.out.println("명령어를 확인 해주세요.");
		}

	}

	private void dojoin() {
		
		
		int id = lastMemberId + 1;
		lastMemberId = id;

		String loginId = null;

		System.out.println("== 회원 가입 ==");
		while (true) {

			System.out.printf("ID : ");
			loginId = sc.nextLine();

			if (isLoginIdDup(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			System.out.printf("%s은(는) 사용가능한 아이디입니다.\n", loginId);
			break;
		}

		String loginPw = null;
		while (true) {
			System.out.printf("PW : ");
			loginPw = sc.nextLine();
			System.out.printf("PW확인 : ");
			String PwChk = sc.nextLine();

			if (loginPw.equals(PwChk) == false) {
				System.out.println("비밀번호를 확인해주세요");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, loginId, loginPw, name, Util.getDateStr());

		members.add(member);

		System.out.printf("%s회원님이 가입되었습니다.\n", loginId);

	}

	private void dologin() {
		System.out.println("== 로그인 ==");
		
		

		System.out.printf("로그인 ID : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 PW : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("일치하는 회원이 없습니다.");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인 해주세요");
			return;
		}
		
		LoginedMember = member; // 로그인한 정보를 저장하는 거임. 
		
		System.out.printf("%s님 환영합니다.\n", member.name);
		
	}
	
	private void dologout() {
		
				
			LoginedMember = null;
			System.out.println("로그아웃 되었습니다.");
			
		}
		
		
	
	

	private Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return member;
			}
		}
		return null;
	}

	private boolean isLoginIdDup(String loginId) {

		Member member = getMemberByLoginId(loginId);
		if (member != null) {
			return false;

		}
		return true;

	}
	
	
	

	@Override
	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 5개 생성");

		for (int i = 1; i <= 3; i++) {

			int id = lastMemberId + 1;
			lastMemberId = id;

			String loginId = "test" + i;
			String loginPw = "test" + i;
			String name = "사용자" + i;

			Member member = new Member(id, loginId, loginPw, name, Util.getDateStr());
			members.add(member);

		}

	}

}
