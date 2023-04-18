package bam.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Util.Util;
import bam.dto.Article;

public class ArticleController {

	private List<Article> articles;
	private Scanner sc;
	private int lastArticleId;
	

	public ArticleController(List<Article> articles, Scanner sc) {
		this.articles = articles;
		this.sc = sc;
		this.lastArticleId = 0;
	}

	public void dowrite() {
		System.out.println("== 게시글 작성 ==");
		int id = lastArticleId + 1;
		lastArticleId = id;

		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(id, title, body, Util.getDateStr());

		articles.add(article);

		System.out.printf("%d번 글이 생성되었습니다\n", id);

	}

	public void showlist(String cmd) {
		if (articles.size() == 0) {
			System.out.println("존재하는 게시물이 없습니다");
			return;
		}
		List<Article> printArticles = articles;

		String searchKeyword = cmd.substring("article list".length()).trim();

		if (searchKeyword.length() > 0) {
			System.out.println("입력된 검색어 : " + searchKeyword);

			printArticles = new ArrayList<>();

			for (Article article : articles) {
				if (article.title.contains(searchKeyword)) {
					printArticles.add(article);
				}

			}

			if (printArticles.size() == 0) {
				System.out.println("결과 없음");
				return;
			}
		}

		System.out.println("== 게시글 목록 ==");

		System.out.println("번호	|	제목	|		작성날짜");

		for (int i = printArticles.size() - 1; i >= 0; i--) {
			Article article = printArticles.get(i);

			System.out.printf("%d	|	%s	|	%s\n", article.id, article.title, article.regDate);
		}

	}

	public void showdetail(String cmd) {
		String[] cmdBits = cmd.split(" "); // array를 쓸 수 없는 이유는 split이 string의 배열이라 사용 불가.
//		int id = cmdBits[2]; // > cmdBits는 string(문자)이므로  int는 안되서 형변환을 해줘야 한다.
		
		int id = Integer.parseInt(cmdBits[2]);
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
			return;
			
		}
		
		System.out.println("== 게시글 상세보기 ==");
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
		System.out.printf("작성날짜 : %s\n", foundArticle.regDate);
		

	}

	public void domodify(String cmd) {
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
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

	public void dodelete(String cmd) {
		System.out.println("== 게시글 삭제 ==");
		String[] cmdBits = cmd.split(" ");

		int id = Integer.parseInt(cmdBits[2]);

		Article foundArticle = getArticleById(id);

		if (foundArticle == null) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", id);
			return;
		}

		articles.remove(foundArticle); // List, ArrayList 의 요소 삭제 방법 = remove

		System.out.printf("%d번 게시물이 삭제 되었습니다.\n", id);


	}
	
	private Article getArticleById(int id) {
		for (Article article : articles) {
			if (article.id == id) { // 순회해서 일치하는게 있으면
				return article; // 아티클을 리턴하겠다.

			}
		}
		return null;
	}
	
}



