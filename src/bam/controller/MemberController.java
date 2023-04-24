package bam.controller;

import java.util.Scanner;

import Util.Util;
import bam.dto.Member;
import bam.service.MemberService;
import container.Container;

public class MemberController extends Controller {

	private Scanner sc;
	private MemberService memberService;

	public MemberController(Scanner sc) {
		this.memberService = Container.memberService;
		this.sc = sc;

	}

	@Override
	public void doAtcion(String cmd, String methodName) {

		switch (methodName) {
		case "join":
			dojoin();

			break;

		case "login":
			dologin();

			break;
		case "logout":
			dologout();

			break;

		default:
			System.out.println("명령어를 확인 해주세요.");
		}

	}

	private void dojoin() {

		System.out.println("== 회원 가입 ==");

		int id = memberService.setLastId();

		String loginId = null;

		while (true) {

			System.out.printf("ID : ");
			loginId = sc.nextLine();

			if (memberService.isLoginIdDup(loginId) == false) {
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
			String loginPwChk = sc.nextLine();

			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호를 확인해주세요");
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, loginId, loginPw, name, Util.getDateStr());

		memberService.add(member);

		System.out.printf("%s회원님이 가입되었습니다.\n", loginId);

	}

	private void dologin() {
		System.out.println("== 로그인 ==");

		System.out.printf("로그인 ID : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 PW : ");
		String loginPw = sc.nextLine();

		Member member = memberService.getMemberByLoginId(loginId);

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

	@Override
	public void makeTestData() {
		System.out.println("테스트용 회원 데이터 5개 생성");
		memberService.makeTestData();

	}

}
