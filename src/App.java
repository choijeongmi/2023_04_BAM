import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Util.Util;
import bam.controller.ArticleController;
import bam.controller.MemberController;
import bam.dto.Article;
import bam.dto.Member;

public class App {

	private List<Article> articles;
	private List<Member> members;
	private int lastArticleId;
	

	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
		lastArticleId = 0;
		
	}

	public void run() {
		System.out.println("== 프로그램 시작 ==");
		makeTestData();
		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(members, sc);
		ArticleController articleController = new ArticleController(articles, sc);

		while (true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();

			if (cmd.equals("exit")) {
				break;
			}

			if (cmd.equals("member join")) {
				memberController.dojoin();
			}
			
			else if (cmd.equals("article write")) {
				articleController.dowrite();
					
			}


			else if (cmd.startsWith("article list")) {
				articleController.showlist(cmd);
				
				
			}
			else if (cmd.startsWith("article detail ")) {
				articleController.showdetail(cmd);
			} 

			else if (cmd.startsWith("article modify ")) {
				articleController.domodify(cmd);

				
			}
			else if (cmd.startsWith("article delete ")) {
				articleController.dodelete(cmd);
			}

			else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
//				split : 문장 자르기 

		}

		sc.close();

		System.out.println("== 프로그램 끝 ==");

	}

//	private Article getArticleById(int id) {
//		for (Article article : articles) {
//			if (article.id == id) { // 순회해서 일치하는게 있으면
//				return article; // 아티클을 리턴하겠다.
//
//			}
//		}
//		return null;
//	}
	

	private void makeTestData() {
		System.out.println("테스트용 게시물 데이터 5개 생성");

		for (int i = 1; i <= 5; i++) {

			int id = lastArticleId + 1;
			lastArticleId = id;

			String title = "제목" + i;
			String body = "내용" + i;

			Article article = new Article(i, title, body, Util.getDateStr());
			articles.add(article);

		}

	}

}
