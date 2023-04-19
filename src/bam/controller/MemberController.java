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
		
		switch(methodName) {
		case "join":
			dojoin();
			
			break;
		}
		
	}
	

	private void dojoin() {

		System.out.println("== 회원 가입 ==");
		int id = lastMemberId + 1;
		lastMemberId = id;

		String loginId = null;

		while (true) {
//					boolean isLoginIdDup = false;

			System.out.printf("ID : ");
			loginId = sc.nextLine();

			if (isLoginIdDup(loginId) == false) {
				System.out.printf("%s은(는) 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			System.out.printf("%s은(는) 사용가능한 아이디입니다.\n", loginId);
			break;
		}

//					for(Member member : members) {
//						if(member.loginId.equals(loginId)) {
//							isLoginIdDup = true;							
//							break;
//						}
//					}
//					break;
//				}

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

	private boolean isLoginIdDup(String loginId) {
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return false;
			}
		}
		return true;
	}

	

	

}
