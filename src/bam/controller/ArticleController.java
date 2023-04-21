package bam.controller;


import java.util.List;
import java.util.Scanner;

import Util.Util;
import bam.dto.Article;
import bam.service.ArticleService;
import container.Container;

public class ArticleController extends Controller {

	
	private Scanner sc;
	private String cmd;
	private ArticleService articleService;

	
	
	public ArticleController(Scanner sc) {
		
		this.sc = sc;
		this.articleService = Container.articleService;

	}

	@Override
	public void doAtcion(String cmd, String methodName) {
//		controller자체가 abstract 형식이라 자식인 클래스에 추상메서드를 오버라이딩 해줘야 한다		
		this.cmd = cmd;
		switch (methodName) {
		case "write":
			dowrite();
			break;

		case "list":
			showlist();
			break;

		case "detail":
			showdetail();
			break;

		case "modify":
			domodify();
			break;

		case "delete":
			dodelete();
			break;
		default:
			System.out.println("명령어를 확인 해주세요.");
		}
	}

	private void dowrite() {

		System.out.println("== 게시글 작성 ==");
		int id = articleService.setLastId();

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, title, body, LoginedMember.id, Util.getDateStr());

		articleService.add(article);

		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}

	private void showlist() {
		
		String searchKeyword = cmd.substring("article list".length()).trim();
		
		List<Article> printArticles = articleService.getArticles(searchKeyword);
		
		if (printArticles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다");
			return;
		}

		if (printArticles.size() == 0) {
			System.out.println("결과 없음");
			return;
		}

		System.out.println("== 게시글 목록 ==");

		System.out.println("번호	|	제목	|	작성자	|	작성날짜");

		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);

			System.out.printf("%d	|	%s	|	%d	|	%s\n", article.id, article.title, article.memberId,
					article.regDate);
		}

	}

	private void showdetail() {
		String[] cmdBits = cmd.split(" "); // array를 쓸 수 없는 이유는 split이 string의 배열이라 사용 불가.
//		int id = cmdBits[2]; // > cmdBits는 string(문자)이므로  int는 안되서 형변환을 해줘야 한다.

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인 해주세요.");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;

		}

		System.out.println("== 게시글 상세보기 ==");
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("작성자 : %s\n", foundArticle.memberId);
		System.out.printf("작성날짜 : %s\n", foundArticle.regDate);

	}

	private void domodify() {

		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인 해주세요.");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
		}

		if (foundArticle.memberId != LoginedMember.id) {
			System.out.println("게시물 수정 권한이 없습니다.");
			return;
		}

		System.out.println("== 게시글 수정 ==");
		System.out.printf("수정 할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정 할 내용 : ");
		String body = sc.nextLine();
		


		foundArticle.title = title;
		foundArticle.body = body;

		System.out.printf("%d번 게시물이 수정 되었습니다.\n", id);

	}

	private void dodelete() {

		String[] cmdBits = cmd.split(" ");

		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인 해주세요.");
			return;
		}

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}
		if (foundArticle.memberId != LoginedMember.id) {
			System.out.println("게시물 삭제 권한이 없습니다.");
			return;
		}

		articleService.remove(foundArticle); // List, ArrayList 의 요소 삭제 방법 = remove

		System.out.printf("%d번 게시물이 삭제 되었습니다.\n", id);

	}  


	

	@Override
	public void makeTestData() {
		System.out.println("테스트용 게시물 데이터 5개 생성");

	}

}
